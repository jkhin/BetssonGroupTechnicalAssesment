package jk.labs.dev.betsson.group.technical.assesment.data.remote.sources

import jk.labs.dev.betsson.group.technical.assesment.data.entities.requests.PlaceDetailQueryParamsRequest
import jk.labs.dev.betsson.group.technical.assesment.data.entities.requests.PlaceTipsQueryParamsRequest
import jk.labs.dev.betsson.group.technical.assesment.data.entities.requests.PlacesQueryParamsRequest
import jk.labs.dev.betsson.group.technical.assesment.data.entities.responses.PlacePhotoResponse
import jk.labs.dev.betsson.group.technical.assesment.data.entities.responses.NearbyPlacesResponse
import jk.labs.dev.betsson.group.technical.assesment.data.entities.responses.PlaceResponse
import jk.labs.dev.betsson.group.technical.assesment.data.entities.responses.PlaceTipResponse
import jk.labs.dev.betsson.group.technical.assesment.data.remote.SearchAndDataCloudDataSource
import jk.labs.dev.betsson.group.technical.assesment.data.remote.api.SearchAndDataService
import jk.labs.dev.betsson.group.technical.assesment.data.remote.sources.base.BaseDataSource
import javax.inject.Inject

class SearchAndDataCloudDataSourceImpl
@Inject constructor(
    private val service: SearchAndDataService
) : BaseDataSource(), SearchAndDataCloudDataSource {

    override suspend fun getNearbyPlaces(
        queryParams: PlacesQueryParamsRequest
    ): NearbyPlacesResponse = with(queryParams) {
        return getResult {
            service.getNearbyPlaces(
                //customFields = "",
                latLng = ll.orEmpty(),
                radius = radius,
                minPrice = minPrice,
                maxPrice = maxPrice,
                isOpenNow = isOpenNow?: true,
            )
        }
    }

    override suspend fun getPlaceDetails(
        queryParams: PlaceDetailQueryParamsRequest
    ): PlaceResponse {
        return getResult {
            service.getPlaceDetails(
                id = queryParams.fsqId,
                fields = queryParams.fields
            )
        }

    }

    override suspend fun getPlacePhotos(
        fsqId: String
    ): List<PlacePhotoResponse> {
        return getResult {
            service.getPlacePhotos(
                id = fsqId
            )
        }
    }

    override suspend fun getPLaceTips(
        placeTipsQueryParamsRequest: PlaceTipsQueryParamsRequest
    ): List<PlaceTipResponse> {
        return getResult {
            service.getPlaceTips(
                id = placeTipsQueryParamsRequest.fsqId,
                fields = placeTipsQueryParamsRequest.fields
            )
        }
    }

}