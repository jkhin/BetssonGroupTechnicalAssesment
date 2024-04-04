package jk.labs.dev.betsson.group.technical.assesment.data.repositories

import jk.labs.dev.betsson.group.technical.assesment.data.entities.FavoritePlaceEntity
import jk.labs.dev.betsson.group.technical.assesment.data.entities.requests.PlaceDetailQueryParamsRequest
import jk.labs.dev.betsson.group.technical.assesment.data.entities.requests.PlaceTipsQueryParamsRequest
import jk.labs.dev.betsson.group.technical.assesment.data.entities.requests.PlacesQueryParamsRequest
import jk.labs.dev.betsson.group.technical.assesment.data.local.FavoritePlaceLocalDataSource
import jk.labs.dev.betsson.group.technical.assesment.data.mappers.PlaceDetailResultMapper
import jk.labs.dev.betsson.group.technical.assesment.data.mappers.PlacePhotoResultMapper
import jk.labs.dev.betsson.group.technical.assesment.data.mappers.PlaceSearchResultMapper
import jk.labs.dev.betsson.group.technical.assesment.data.mappers.PlaceTipsResultMapper
import jk.labs.dev.betsson.group.technical.assesment.data.remote.SearchAndDataCloudDataSource
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PhotoResult
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.Place
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PlaceDetailQueryParams
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PlaceTipResult
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PlaceTipsQueryParams
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PlacesQueryParams
import jk.labs.dev.betsson.group.technical.assesment.domain.repositories.SearchAndDataRepository
import javax.inject.Inject

class SearchAndDataRepositoryImpl
@Inject constructor(
    private val searchAndDataCloudDataSource: SearchAndDataCloudDataSource,
    private val favoritePlaceLocalDataSource: FavoritePlaceLocalDataSource,
    private val placeSearchResultMapper: PlaceSearchResultMapper,
    private val placeDetailResultMapper: PlaceDetailResultMapper,
    private val placeTipsResultMapper: PlaceTipsResultMapper,
    private val placePhotoResultMapper: PlacePhotoResultMapper
) : SearchAndDataRepository {

    override suspend fun getNearbyPlaces(
        placesQueryParams: PlacesQueryParams
    ): List<Place> {
        val queryParamsRequest = getQueryParamsRequest(placesQueryParams)
        val results = searchAndDataCloudDataSource
            .getNearbyPlaces(queryParamsRequest)
        return results.results.map(placeSearchResultMapper::map)
    }

    override suspend fun getPlaceDetail(
        placeDetailQueryParams: PlaceDetailQueryParams
    ): Place {
        val queryParamsRequest = getQueryParamsRequest(placeDetailQueryParams)
        val result = searchAndDataCloudDataSource.getPlaceDetails(
            queryParamsRequest
        )
        val place = placeDetailResultMapper.map(result)
        val favorites = favoritePlaceLocalDataSource.getFavoritePlace()
        if (favorites.isNotEmpty())
            favorites
                .any { it.fsqId == result.id }
                .also { place.isFavorite = it }
        return place
    }

    override suspend fun getPlaceTips(
        placeTipsQueryParams: PlaceTipsQueryParams
    ): List<PlaceTipResult> = searchAndDataCloudDataSource
        .getPLaceTips(getQueryParamsRequest(placeTipsQueryParams))
        .map(placeTipsResultMapper::map)

    override suspend fun getPlacePhotos(
        fsqId: String
    ): List<PhotoResult> {
        val results = searchAndDataCloudDataSource.getPlacePhotos(fsqId = fsqId)
        return results.map(placePhotoResultMapper::map)
    }

    private fun getQueryParamsRequest(
        placesQueryParams: PlacesQueryParams
    ): PlacesQueryParamsRequest = PlacesQueryParamsRequest(
        customResponseFields = placesQueryParams.customResponseFields,
        ll = placesQueryParams.ll,
        minPrice = placesQueryParams.minPrice.minValue,
        maxPrice = placesQueryParams.maxPrice.minValue,
        isOpenNow = placesQueryParams.isOpenNow,
        radius = placesQueryParams.radius
    )

    private fun getQueryParamsRequest(
        placeDetailQueryParams: PlaceDetailQueryParams
    ): PlaceDetailQueryParamsRequest = PlaceDetailQueryParamsRequest(
        fsqId = placeDetailQueryParams.fsqId,
        fields = placeDetailQueryParams.customResponseFields
    )

    private fun getQueryParamsRequest(
        placeTipsQueryParams: PlaceTipsQueryParams
    ): PlaceTipsQueryParamsRequest = PlaceTipsQueryParamsRequest(
        fsqId = placeTipsQueryParams.fsqId,
        fields = placeTipsQueryParams.customResponseFields
    )
}