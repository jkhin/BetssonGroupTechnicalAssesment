package jk.labs.dev.betsson.group.technical.assesment.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jk.labs.dev.betsson.group.technical.assesment.domain.FilterPlacesUseCase
import jk.labs.dev.betsson.group.technical.assesment.domain.GetNearbyPlacesUseCase
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.FilterQueryParams
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PlacesQueryParams
import jk.labs.dev.betsson.group.technical.assesment.ui.mappers.PlaceModelMapper
import jk.labs.dev.betsson.group.technical.assesment.ui.models.PlaceGridItemModel
import jk.labs.dev.betsson.group.technical.assesment.ui.models.PlacesFilterModel
import jk.labs.dev.betsson.group.technical.assesment.ui.states.PlacesUiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NearbyPlacesViewModel
@Inject constructor(
    private val getNearbyPlacesUseCase: GetNearbyPlacesUseCase,
    private val filterPlacesUseCase: FilterPlacesUseCase,
    private val placeModelMapper: PlaceModelMapper
) : ViewModel() {

    private val _uiState = MutableLiveData<PlacesUiState>()
    val uiState: LiveData<PlacesUiState>
        get() = _uiState

    private var currentLocation: Pair<Double, Double>? = null

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        viewModelScope.launch {
            Log.e("Error =>", exception.message.orEmpty())
            val errorModelMapper = exception.message.orEmpty()
            postErrorState(errorModelMapper)
        }
    }

    fun getNearbyPlaces() {
        viewModelScope.launch(Dispatchers.IO + errorHandler) {
            withContext(Dispatchers.Main) {
                postLoadingState(isLoading = true)
            }
            val results = getNearbyPlacesUseCase.getNearbyPlaces(getQueryParams())
            val models = results.map(placeModelMapper::map)
            withContext(Dispatchers.Main) {
                postLoadingState(isLoading = false)
                postSuccessState(items = models)
            }
        }
    }

    fun filterNearbyPlaces(query: PlacesFilterModel) {
        viewModelScope.launch(Dispatchers.IO + errorHandler) {
            withContext(Dispatchers.Main) {
                postLoadingState(isLoading = true)
            }
            val queryParams = FilterQueryParams(
                isOpen = query.isOpen,
                pricingScale = query.priceScale
            )
            val models = filterPlacesUseCase
                .filter(queryParams)
                .map(placeModelMapper::map)

            withContext(Dispatchers.Main) {
                postLoadingState(isLoading = false)
                postSuccessState(items = models)
            }
        }
    }

    fun setCurrentLocation(currentLocation: Pair<Double, Double>) {
        this.currentLocation = currentLocation
    }

    private fun getLoadingUiState(
        isLoading: Boolean
    ): PlacesUiState = PlacesUiState.Loading(isLoading = isLoading)

    private fun getSuccessUiState(
        items: List<PlaceGridItemModel>
    ): PlacesUiState = PlacesUiState.Success(items = items)

    private fun getErrorUiState(
        errorMessage: String
    ): PlacesUiState = PlacesUiState.Error(errorMessage = errorMessage)

    private fun postSuccessState(
        items: List<PlaceGridItemModel>
    ) {
        _uiState.value = getSuccessUiState(items = items)
    }

    private fun postLoadingState(
        isLoading: Boolean
    ) {
        _uiState.value = getLoadingUiState(isLoading = isLoading)
    }

    private fun postErrorState(
        errorMessage: String
    ) {
        _uiState.value = getErrorUiState(errorMessage = errorMessage)
    }

    private fun getQueryParams(): PlacesQueryParams {
        val lat = currentLocation?.first
        val lng = currentLocation?.second
        return PlacesQueryParams(ll = if (lat != null) "$lat,$lng" else "")
    }

}