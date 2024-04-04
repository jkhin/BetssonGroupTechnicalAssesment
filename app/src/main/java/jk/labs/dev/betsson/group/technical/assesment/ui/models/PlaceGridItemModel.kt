package jk.labs.dev.betsson.group.technical.assesment.ui.models

import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PricingScale

data class PlaceGridItemModel(
    val id: String,
    val distance: String,
    val name: String,
    val pricingScale: PricingScale,
    val rating: String,
    val isFavorite: Boolean,
)