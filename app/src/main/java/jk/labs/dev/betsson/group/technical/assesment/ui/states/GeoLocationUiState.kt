package jk.labs.dev.betsson.group.technical.assesment.ui.states

sealed class GeoLocationUiState {
    data object GetCurrentLocation: GeoLocationUiState()
    data class UpdatedGeolocation(val currLocation: Pair<Double, Double>): GeoLocationUiState()
}