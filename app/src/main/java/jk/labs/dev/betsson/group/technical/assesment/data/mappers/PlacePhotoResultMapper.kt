package jk.labs.dev.betsson.group.technical.assesment.data.mappers

import jk.labs.dev.betsson.group.technical.assesment.data.entities.responses.PlacePhotoResponse
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PhotoResult
import jk.labs.dev.betsson.group.technical.assesment.utils.mappers.Mapper

class PlacePhotoResultMapper : Mapper<PlacePhotoResponse, PhotoResult>() {
    override fun map(entry: PlacePhotoResponse): PhotoResult {
        return PhotoResult(
            prefix = entry.imgPrefix, suffix = entry.imgSuffix
        )
    }
}