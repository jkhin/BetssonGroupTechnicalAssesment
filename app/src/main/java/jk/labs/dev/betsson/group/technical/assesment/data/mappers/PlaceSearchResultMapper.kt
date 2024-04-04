package jk.labs.dev.betsson.group.technical.assesment.data.mappers

import jk.labs.dev.betsson.group.technical.assesment.data.entities.responses.PlaceResponse
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.Place
import jk.labs.dev.betsson.group.technical.assesment.utils.mappers.Mapper

class PlaceSearchResultMapper: Mapper<PlaceResponse, Place>() {
    override fun map(entry: PlaceResponse): Place {
        return Place(
            id = entry.id,
            name = entry.name,
            distance = entry.distance,
            pricingScale = entry.pricingScale,
            rating = entry.rating,
            email = entry.email,
            isOpenNow = entry.hours?.isOpenNow?: false,
            address = entry.location.address,
            description = entry.description,
            phoneNumber = entry.phoneNumber
        )
    }
}