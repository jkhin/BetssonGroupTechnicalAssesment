package jk.labs.dev.betsson.group.technical.assesment.data.entities.responses

import com.google.gson.annotations.SerializedName

data class PlaceResponse(
    @SerializedName("fsq_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("tel")
    val phoneNumber: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("price")
    val pricingScale: Int,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("distance")
    val distance: Int,
    @SerializedName("hours")
    val hours: HoursResponse?,
    @SerializedName("location")
    val location: LocationResponse
) {
    data class HoursResponse(
        @SerializedName("open_now")
        val isOpenNow: Boolean
    )

    data class LocationResponse(
        @SerializedName("formatted_address")
        val address: String
    )
}
