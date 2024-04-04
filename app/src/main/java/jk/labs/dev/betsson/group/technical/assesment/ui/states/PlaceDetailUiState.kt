package jk.labs.dev.betsson.group.technical.assesment.ui.states

import jk.labs.dev.betsson.group.technical.assesment.ui.models.PhotoModel
import jk.labs.dev.betsson.group.technical.assesment.ui.models.PlaceDetailModel
import jk.labs.dev.betsson.group.technical.assesment.ui.models.PlaceTipModel

sealed class PlaceDetailUiState {
    class Loading(val isLoading: Boolean) : PlaceDetailUiState()
    class Success(
        val model: Triple<PlaceDetailModel, List<PhotoModel>, List<PlaceTipModel>>
    ) : PlaceDetailUiState()

    class Error(val errorMessage: String) : PlaceDetailUiState()
}