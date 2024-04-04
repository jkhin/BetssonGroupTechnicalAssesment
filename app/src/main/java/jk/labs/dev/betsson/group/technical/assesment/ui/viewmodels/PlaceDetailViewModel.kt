package jk.labs.dev.betsson.group.technical.assesment.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jk.labs.dev.betsson.group.technical.assesment.domain.GetPlaceDetailUseCase
import jk.labs.dev.betsson.group.technical.assesment.domain.SetPlaceAsFavoriteUseCase
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PlaceDetailQueryParams
import jk.labs.dev.betsson.group.technical.assesment.ui.mappers.PlaceDetailModelMapper
import jk.labs.dev.betsson.group.technical.assesment.ui.mappers.PlacePhotoModelMapper
import jk.labs.dev.betsson.group.technical.assesment.ui.mappers.PlaceTipModelMapper
import jk.labs.dev.betsson.group.technical.assesment.ui.models.PhotoModel
import jk.labs.dev.betsson.group.technical.assesment.ui.models.PlaceDetailModel
import jk.labs.dev.betsson.group.technical.assesment.ui.models.PlaceTipModel
import jk.labs.dev.betsson.group.technical.assesment.ui.states.PlaceDetailUiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PlaceDetailViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val getPlaceDetailUseCase: GetPlaceDetailUseCase,
    private val setFavoritePlaceUseCase: SetPlaceAsFavoriteUseCase,
    private val placeDetailModelMapper: PlaceDetailModelMapper,
    private val placePhotoModelMapper: PlacePhotoModelMapper,
    private val placeTipsModelMapper: PlaceTipModelMapper
) : ViewModel() {

    private val _placeDetailLiveData = MutableLiveData<PlaceDetailUiState>()
    val placeDetailsLiveData
        get() = _placeDetailLiveData

    private val fsqId: String
        get() = stateHandle["fsqId"] ?: ""

    private var phoneNumber: String = ""

    private var isFavorite: Boolean = false

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        viewModelScope.launch {
            val errorMessage = exception.message.orEmpty()
            postErrorState(errorMessage)
            Log.e("Error =>", errorMessage)
        }
    }

    fun getPlaceDetail() {
        viewModelScope.launch(Dispatchers.IO + errorHandler) {
            withContext(Dispatchers.Main) {
                postLoadingState(true)
            }
            val queryParams = PlaceDetailQueryParams(fsqId = fsqId)
            val result = getPlaceDetailUseCase.getPlaceDetail(queryParams)
            phoneNumber = result.first.phoneNumber.orEmpty()
            val model = Triple(
                placeDetailModelMapper.map(result.first),
                result.second.map(placePhotoModelMapper::map),
                result.third.map(placeTipsModelMapper::map)
            )
            isFavorite = model.first.isFavorite
            withContext(Dispatchers.Main) {
                postLoadingState(false)
                postSuccessState(model)
            }
        }
    }

    fun setPlaceAsFavorite(isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO + errorHandler) {
            setFavoritePlaceUseCase.setPlaceAsFavorite(fsqId = fsqId, isFavorite = isFavorite)
        }
    }

    fun getPlacePhoneNumber(): String = phoneNumber

    private fun getLoadingUiState(
        isLoading: Boolean
    ): PlaceDetailUiState.Loading = PlaceDetailUiState.Loading(isLoading = isLoading)

    private fun getSuccessUiState(
        model: Triple<PlaceDetailModel, List<PhotoModel>, List<PlaceTipModel>>
    ): PlaceDetailUiState.Success = PlaceDetailUiState.Success(model = model)

    private fun getErrorUiState(
        errorMessage: String
    ): PlaceDetailUiState.Error = PlaceDetailUiState.Error(errorMessage = errorMessage)

    private fun postSuccessState(
        model: Triple<PlaceDetailModel, List<PhotoModel>, List<PlaceTipModel>>
    ) {
        _placeDetailLiveData.postValue(getSuccessUiState(model = model))
    }

    private fun postLoadingState(
        isLoading: Boolean
    ) {
        _placeDetailLiveData.value =
            getLoadingUiState(isLoading = isLoading)
    }


    private fun postErrorState(
        errorMessage: String
    ) {
        _placeDetailLiveData.value =
            getErrorUiState(errorMessage = errorMessage)
    }

}