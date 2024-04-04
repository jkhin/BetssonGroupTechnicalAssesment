package jk.labs.dev.betsson.group.technical.assesment.domain.usecases

import jk.labs.dev.betsson.group.technical.assesment.domain.GetPlaceDetailUseCase
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PhotoResult
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.Place
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PlaceDetailQueryParams
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PlaceTipResult
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PlaceTipsQueryParams
import jk.labs.dev.betsson.group.technical.assesment.domain.repositories.SearchAndDataRepository
import javax.inject.Inject

class GetPlaceDetailUseCaseImpl
@Inject constructor(
    private val searchAndDataRepository: SearchAndDataRepository
) : GetPlaceDetailUseCase {
    override suspend fun getPlaceDetail(
        queryParams: PlaceDetailQueryParams
    ): Triple<Place, List<PhotoResult>, List<PlaceTipResult>> {
        val detail = searchAndDataRepository.getPlaceDetail(queryParams)
        val gallery = searchAndDataRepository.getPlacePhotos(queryParams.fsqId)
        val tips = searchAndDataRepository.getPlaceTips(
            PlaceTipsQueryParams(fsqId = queryParams.fsqId)
        )
        return Triple(detail, gallery, tips)
    }

}