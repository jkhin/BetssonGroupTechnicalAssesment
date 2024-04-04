package jk.labs.dev.betsson.group.technical.assesment.domain.entities

enum class PlaceAvailability(val isOpenNow: Boolean, val value: String) {
    OPEN(
        isOpenNow = true,
        value = "Open"
    ),
    CLOSED(
        isOpenNow = false,
        value = "Closed"
    )
}