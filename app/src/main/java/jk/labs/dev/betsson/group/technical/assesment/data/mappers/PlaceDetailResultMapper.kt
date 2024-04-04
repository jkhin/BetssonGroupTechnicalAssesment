package jk.labs.dev.betsson.group.technical.assesment.data.mappers

import jk.labs.dev.betsson.group.technical.assesment.data.entities.responses.PlaceResponse
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.Place
import jk.labs.dev.betsson.group.technical.assesment.utils.mappers.Mapper

class PlaceDetailResultMapper : Mapper<PlaceResponse, Place>() {
    override fun map(
        entry: PlaceResponse
    ): Place = Place(
        email = entry.email,
        name = entry.name,
        description = entry.description,
        pricingScale = entry.pricingScale,
        rating = entry.rating,
        phoneNumber = entry.phoneNumber,
        isOpenNow = entry.hours?.isOpenNow?: false,
        address = entry.location.address,
        distance = entry.distance,
        id = entry.id
    )
}