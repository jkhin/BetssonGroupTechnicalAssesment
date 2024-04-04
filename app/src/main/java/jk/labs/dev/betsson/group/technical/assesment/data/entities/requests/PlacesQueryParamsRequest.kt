package jk.labs.dev.betsson.group.technical.assesment.data.entities.requests

data class PlacesQueryParamsRequest(
    val customResponseFields: String,
    val ll: String?,
    val isOpenNow: Boolean?,
    val minPrice: Int,
    val maxPrice: Int,
    val radius: Int,
)
