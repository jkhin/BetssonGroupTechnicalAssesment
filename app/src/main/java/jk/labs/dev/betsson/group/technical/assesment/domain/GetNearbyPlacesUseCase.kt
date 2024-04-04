package jk.labs.dev.betsson.group.technical.assesment.domain

import jk.labs.dev.betsson.group.technical.assesment.domain.entities.Place
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PlacesQueryParams

interface GetNearbyPlacesUseCase {
    suspend fun getNearbyPlaces(queryParams: PlacesQueryParams): List<Place>
}