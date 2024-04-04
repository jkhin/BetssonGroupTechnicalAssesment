package jk.labs.dev.betsson.group.technical.assesment.data.entities.responses

import com.google.gson.annotations.SerializedName

data class NearbyPlacesResponse(
    @SerializedName("results") val results: List<PlaceResponse>
)
