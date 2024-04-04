package jk.labs.dev.betsson.group.technical.assesment.domain.entities

import jk.labs.dev.betsson.group.technical.assesment.BuildConfig

data class PlacesQueryParams(
    val ll: String = "",
    val isOpenNow: Boolean? = true,
    val minPrice: PricingScale = PricingScale.MOST_AFFORDABLE,
    val maxPrice: PricingScale = PricingScale.MOST_EXPENSIVE,
    val radius: Int = BuildConfig.FSQ_RADIUS
) {
    val customResponseFields: String
        get() = getResponseFields()

    private val responseFields: List<String> = listOf(
        "fsq_id",
        "distance",
        "name",
        "rating",
        "price",
    )

    private fun getResponseFields(): String {
        val result = StringBuilder()
        responseFields.forEach { result.append(it.plus(",")) }
        return result.toString()
    }
}
