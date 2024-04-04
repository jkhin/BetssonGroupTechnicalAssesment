package jk.labs.dev.betsson.group.technical.assesment.domain.entities

enum class PricingScale(
    val minValue: Int,
    val rangeValue: String
) {
    MOST_AFFORDABLE(
        minValue = 1,
        rangeValue = "$"
    ),
    AFFORDABLE(
        minValue = 2,
        rangeValue = "$$"
    ),
    EXPENSIVE(
        minValue = 3,
        rangeValue = "$$$"
    ),
    MOST_EXPENSIVE(
        minValue = 4,
        rangeValue = "$$$$"
    );
}