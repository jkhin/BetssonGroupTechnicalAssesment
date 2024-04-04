package jk.labs.dev.betsson.group.technical.assesment.domain.entities

data class PlaceDetailQueryParams(
    val fsqId: String
) {
    val customResponseFields: String
        get() = getResponseFields()

    private val responseFields: List<String> = listOf(
        "fsq_id",
        "email",
        "name",
        "description",
        "categories",
        "hours",
        "price",
        "rating",
        "tel",
        "location",
    )

    private fun getResponseFields(): String {
        val result = StringBuilder()
        responseFields.forEach { result.append(it.plus(",")) }
        return result.toString()
    }
}
