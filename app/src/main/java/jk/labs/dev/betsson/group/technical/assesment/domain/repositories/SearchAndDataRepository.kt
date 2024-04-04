package jk.labs.dev.betsson.group.technical.assesment.domain.repositories

import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PhotoResult
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.Place
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PlaceDetailQueryParams
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PlaceTipResult
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PlaceTipsQueryParams
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PlacesQueryParams

interface SearchAndDataRepository {

    suspend fun getNearbyPlaces(placesQueryParams: PlacesQueryParams): List<Place>

    suspend fun getPlaceDetail(placeDetailQueryParams: PlaceDetailQueryParams): Place

    suspend fun getPlaceTips(placeTipsQueryParams: PlaceTipsQueryParams): List<PlaceTipResult>

    suspend fun getPlacePhotos(fsqId: String): List<PhotoResult>

}