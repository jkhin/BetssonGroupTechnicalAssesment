package jk.labs.dev.betsson.group.technical.assesment.ui.mappers

import jk.labs.dev.betsson.group.technical.assesment.domain.entities.Place
import jk.labs.dev.betsson.group.technical.assesment.ui.models.PlaceDetailModel
import jk.labs.dev.betsson.group.technical.assesment.utils.extensions.mapToOStringValue
import jk.labs.dev.betsson.group.technical.assesment.utils.extensions.mapToPricingScale
import jk.labs.dev.betsson.group.technical.assesment.utils.mappers.Mapper

class PlaceDetailModelMapper: Mapper<Place, PlaceDetailModel>() {
    override fun map(
        entry: Place
    ): PlaceDetailModel = PlaceDetailModel(
        email = entry.email.orEmpty(),
        name = entry.name,
        description = entry.description.orEmpty(),
        pricingScale = entry.pricingScale.mapToPricingScale(),
        rating = entry.rating.toString(),
        phoneNumber = entry.phoneNumber.orEmpty(),
        isOpenNow = entry.isOpenNow.mapToOStringValue(),
        address = entry.address.orEmpty(),
        isFavorite = entry.isFavorite?: false
    )
}