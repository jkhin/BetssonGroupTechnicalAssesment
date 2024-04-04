package jk.labs.dev.betsson.group.technical.assesment.data.entities.responses

import com.google.gson.annotations.SerializedName

data class PlaceTipResponse(
    @SerializedName("agree_count")
    val agreeCount: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("disagree_count")
    val disagreeCount: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("lang")
    val lang: String,
    @SerializedName("photo")
    val photo: PhotoResponse?,
    @SerializedName("text")
    val text: String,
)

data class PhotoResponse(
    @SerializedName("suffix")
    val suffix: String,
    @SerializedName("prefix")
    val prefix: String
)
