package jk.labs.dev.betsson.group.technical.assesment.data.remote

import jk.labs.dev.betsson.group.technical.assesment.data.entities.requests.PlaceDetailQueryParamsRequest
import jk.labs.dev.betsson.group.technical.assesment.data.entities.requests.PlaceTipsQueryParamsRequest
import jk.labs.dev.betsson.group.technical.assesment.data.entities.requests.PlacesQueryParamsRequest
import jk.labs.dev.betsson.group.technical.assesment.data.entities.responses.NearbyPlacesResponse
import jk.labs.dev.betsson.group.technical.assesment.data.entities.responses.PlacePhotoResponse
import jk.labs.dev.betsson.group.technical.assesment.data.entities.responses.PlaceResponse
import jk.labs.dev.betsson.group.technical.assesment.data.entities.responses.PlaceTipResponse

interface SearchAndDataCloudDataSource {
    suspend fun getNearbyPlaces(
        queryParams: PlacesQueryParamsRequest
    ): NearbyPlacesResponse

    suspend fun getPlaceDetails(
        queryParams: PlaceDetailQueryParamsRequest
    ): PlaceResponse

    suspend fun getPlacePhotos(fsqId: String): List<PlacePhotoResponse>

    suspend fun getPLaceTips(
        placeTipsQueryParamsRequest: PlaceTipsQueryParamsRequest
    ): List<PlaceTipResponse>
}