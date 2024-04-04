package jk.labs.dev.betsson.group.technical.assesment.data.remote.api

import jk.labs.dev.betsson.group.technical.assesment.BuildConfig
import jk.labs.dev.betsson.group.technical.assesment.data.entities.responses.NearbyPlacesResponse
import jk.labs.dev.betsson.group.technical.assesment.data.entities.responses.PlacePhotoResponse
import jk.labs.dev.betsson.group.technical.assesment.data.entities.responses.PlaceResponse
import jk.labs.dev.betsson.group.technical.assesment.data.entities.responses.PlaceTipResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchAndDataService {

    @GET("search")
    suspend fun getNearbyPlaces(
        @Query("ll") latLng: String,
        @Query("radius") radius: Int,
        @Query("min_price") minPrice: Int,
        @Query("max_price") maxPrice: Int,
        @Query("open_now") isOpenNow: Boolean,
        @Query("limit") limit: Int = BuildConfig.FSQ_LIMIT
    ): Response<NearbyPlacesResponse>

    @GET("{fsqid}")
    suspend fun getPlaceDetails(
        @Path("fsqid") id: String,
        @Query("fields") fields: String
    ): Response<PlaceResponse>

    @GET("{fsqid}/photos")
    suspend fun getPlacePhotos(
        @Path("fsqid") id: String,
        @Query("limit") limit: Int = BuildConfig.FSQ_LIMIT
    ): Response<List<PlacePhotoResponse>>

    @GET("{fsqid}/tips")
    suspend fun getPlaceTips(
        @Path("fsqid") id: String,
        @Query("fields") fields: String,
        @Query("limit") limit: Int = BuildConfig.FSQ_LIMIT
    ): Response<List<PlaceTipResponse>>

}