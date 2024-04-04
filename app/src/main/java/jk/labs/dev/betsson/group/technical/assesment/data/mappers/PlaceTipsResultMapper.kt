package jk.labs.dev.betsson.group.technical.assesment.data.mappers

import jk.labs.dev.betsson.group.technical.assesment.data.entities.responses.PlaceTipResponse
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PhotoResult
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PlaceTipResult
import jk.labs.dev.betsson.group.technical.assesment.utils.mappers.Mapper

class PlaceTipsResultMapper: Mapper<PlaceTipResponse, PlaceTipResult>() {
    override fun map(entry: PlaceTipResponse): PlaceTipResult {
        return PlaceTipResult(
            agreeCount = entry.agreeCount,
            disagreeCount = entry.disagreeCount,
            id = entry.id,
            createdAt = entry.createdAt,
            lang = entry.lang,
            photo = PhotoResult(
                suffix = entry.photo?.suffix.orEmpty(),
                prefix = entry.photo?.prefix.orEmpty(),
            ),
            text = entry.text
        )
    }
}