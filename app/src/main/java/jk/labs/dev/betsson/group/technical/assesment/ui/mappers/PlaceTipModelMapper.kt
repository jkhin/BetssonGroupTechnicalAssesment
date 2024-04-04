package jk.labs.dev.betsson.group.technical.assesment.ui.mappers

import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PlaceTipResult
import jk.labs.dev.betsson.group.technical.assesment.ui.models.PhotoModel
import jk.labs.dev.betsson.group.technical.assesment.ui.models.PlaceTipModel
import jk.labs.dev.betsson.group.technical.assesment.utils.extensions.prepareAndGetPhotoUrl
import jk.labs.dev.betsson.group.technical.assesment.utils.mappers.Mapper

class PlaceTipModelMapper : Mapper<PlaceTipResult, PlaceTipModel>() {
    override fun map(entry: PlaceTipResult): PlaceTipModel {
        return PlaceTipModel(
            agreeCount = entry.agreeCount,
            disagreeCount = entry.disagreeCount,
            id = entry.id,
            createdAt = entry.createdAt,
            lang = entry.lang.orEmpty(),
            photo = PhotoModel(
                url = prepareAndGetPhotoUrl(
                    imgDimension = DIMENSION,
                    prefix = entry.photo.prefix,
                    suffix = entry.photo.suffix
                )
            ),
            text = entry.text
        )
    }


    companion object {
        private const val DIMENSION = "75x75"
    }

}