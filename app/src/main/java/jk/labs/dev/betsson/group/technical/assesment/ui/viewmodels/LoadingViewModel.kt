package jk.labs.dev.betsson.group.technical.assesment.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoadingViewModel
@Inject constructor() : ViewModel() {
    private val _loadingUiState = MutableLiveData<Boolean>()
    val loadingUiState get() = _loadingUiState

    fun updateLoadingUiState(isLoading: Boolean) {
        loadingUiState.value = isLoading
    }
}