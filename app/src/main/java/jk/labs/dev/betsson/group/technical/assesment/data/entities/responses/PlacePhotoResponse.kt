package jk.labs.dev.betsson.group.technical.assesment.data.entities.responses

import com.google.gson.annotations.SerializedName


data class PlacePhotoResponse(
    @SerializedName("id")
    val id: String, //  "5a64de4fc47cf94d6b63c337",
    @SerializedName("created_at")
    val createdAt: String,  // "2018-01-21T18:39:11.000Z",
    @SerializedName("prefix")
    val imgPrefix: String, // "https://fastly.4sqi.net/img/general/",
    @SerializedName("suffix")
    val imgSuffix: String, // : "/3268843_w83z4HiRhVrLy-J3GfKkru7fF1uUv6trz2v-xFOVj9M.jpg",
    @SerializedName("width")
    val width: Int, // 1440,
    @SerializedName("height")
    val height: Int,
    @SerializedName("classifications")
    val classifications: List<String>
)

