package jk.labs.dev.betsson.group.technical.assesment.domain.entities

data class PlaceTipsQueryParams(
    val fsqId: String
) {
    val customResponseFields: String
        get() = getResponseFields()

    private val responseFields: List<String> = listOf(
        "id",
        "photo",
        "created_at",
        "url",
        "text"
    )

    private fun getResponseFields(): String {
        val result = StringBuilder()
        responseFields.forEach { result.append(it.plus(",")) }
        return result.toString()
    }
}
