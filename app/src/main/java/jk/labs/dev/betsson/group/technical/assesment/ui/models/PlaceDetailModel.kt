package jk.labs.dev.betsson.group.technical.assesment.ui.models

import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PricingScale

data class PlaceDetailModel(
    val email: String,
    val name: String,
    val description: String,
    val pricingScale: PricingScale,
    val rating: String,
    val phoneNumber: String,
    val isOpenNow: String,
    val address: String,
    val isFavorite: Boolean,
)