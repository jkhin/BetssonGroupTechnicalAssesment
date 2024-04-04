package jk.labs.dev.betsson.group.technical.assesment.domain.entities

data class Place(
    val id: String,
    val distance: Int,
    val name: String,
    val pricingScale: Int,
    val rating: Double,
    val email: String?,
    val description: String?,
    val phoneNumber: String?,
    val isOpenNow: Boolean,
    val address: String?
) {
    var isFavorite: Boolean? = null
}
