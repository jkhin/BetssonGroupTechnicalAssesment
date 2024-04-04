package jk.labs.dev.betsson.group.technical.assesment.ui.models

import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PricingScale

data class PlacesFilterModel(
    val priceScale: PricingScale?,
    val isOpen: Boolean?
)
