package jk.labs.dev.betsson.group.technical.assesment.ui.states

import jk.labs.dev.betsson.group.technical.assesment.ui.models.PlaceGridItemModel

sealed class PlacesUiState {
    class Loading(val isLoading: Boolean): PlacesUiState()
    class Success(val items: List<PlaceGridItemModel>): PlacesUiState()
    class Error(val errorMessage: String): PlacesUiState()
}