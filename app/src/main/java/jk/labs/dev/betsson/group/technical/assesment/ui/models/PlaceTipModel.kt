package jk.labs.dev.betsson.group.technical.assesment.ui.models

data class PlaceTipModel(
    val agreeCount: Int,
    val createdAt: String,
    val disagreeCount: Int,
    val id: String,
    val lang: String?,
    val photo: PhotoModel,
    val text: String,
)