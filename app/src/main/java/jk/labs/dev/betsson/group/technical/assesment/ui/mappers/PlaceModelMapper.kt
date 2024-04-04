package jk.labs.dev.betsson.group.technical.assesment.ui.mappers

import jk.labs.dev.betsson.group.technical.assesment.domain.entities.Place
import jk.labs.dev.betsson.group.technical.assesment.ui.models.PlaceGridItemModel
import jk.labs.dev.betsson.group.technical.assesment.utils.extensions.mapToPricingScale
import jk.labs.dev.betsson.group.technical.assesment.utils.mappers.Mapper

class PlaceModelMapper: Mapper<Place, PlaceGridItemModel>() {
    override fun map(entry: Place): PlaceGridItemModel {
        return PlaceGridItemModel(
            id = entry.id,
            name = entry.name,
            pricingScale = entry.pricingScale.mapToPricingScale(),
            distance = mapDistanceToDisplay(entry.distance),
            rating = entry.rating.toString(),
            isFavorite = entry.isFavorite?: false
        )
    }

    private fun convertMetersToKilometers(distance: Int): Double {
        val result: Double = distance.toDouble() / 1000
        return String.format("%.1f", result).toDouble()
    }
    private fun mapDistanceToDisplay(distance: Int): String {
        val formattedValue = convertMetersToKilometers(distance)
        return formattedValue.toString()
    }

}