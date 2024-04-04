package jk.labs.dev.betsson.group.technical.assesment.domain.entities

data class PlaceTipResult(
    val agreeCount: Int,
    val createdAt: String,
    val disagreeCount: Int,
    val id: String,
    val lang: String?,
    val photo: PhotoResult,
    val text: String,
)
