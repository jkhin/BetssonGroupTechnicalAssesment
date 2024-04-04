package jk.labs.dev.betsson.group.technical.assesment.ui.mappers

import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PhotoResult
import jk.labs.dev.betsson.group.technical.assesment.ui.models.PhotoModel
import jk.labs.dev.betsson.group.technical.assesment.utils.extensions.prepareAndGetPhotoUrl
import jk.labs.dev.betsson.group.technical.assesment.utils.mappers.Mapper

class PlacePhotoModelMapper : Mapper<PhotoResult, PhotoModel>() {
    override fun map(entry: PhotoResult): PhotoModel {
        return PhotoModel(
            url = prepareAndGetPhotoUrl(
                imgDimension = DIMENSION,
                entry.prefix,
                entry.suffix
            )
        )
    }

    companion object {
        private const val DIMENSION = "288x288"
    }
}