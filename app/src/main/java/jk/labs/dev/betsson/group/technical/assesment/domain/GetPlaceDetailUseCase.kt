package jk.labs.dev.betsson.group.technical.assesment.domain

import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PhotoResult
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.Place
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PlaceDetailQueryParams
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PlaceTipResult

interface GetPlaceDetailUseCase {
    suspend fun getPlaceDetail(queryParams: PlaceDetailQueryParams): Triple<Place, List<PhotoResult>, List<PlaceTipResult>>
}