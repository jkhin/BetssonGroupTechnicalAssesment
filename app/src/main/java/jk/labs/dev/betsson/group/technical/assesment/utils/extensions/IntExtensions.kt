package jk.labs.dev.betsson.group.technical.assesment.utils.extensions

import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PricingScale

fun Int.mapToPricingScale(): PricingScale = when (this) {
    1 -> PricingScale.MOST_AFFORDABLE
    2 -> PricingScale.AFFORDABLE
    3 -> PricingScale.EXPENSIVE
    else -> PricingScale.MOST_EXPENSIVE
}