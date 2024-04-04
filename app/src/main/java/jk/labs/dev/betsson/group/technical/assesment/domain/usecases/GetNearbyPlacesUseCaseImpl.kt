package jk.labs.dev.betsson.group.technical.assesment.domain.usecases

import jk.labs.dev.betsson.group.technical.assesment.domain.GetNearbyPlacesUseCase
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.FilterQueryParams
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.Place
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PlacesQueryParams
import jk.labs.dev.betsson.group.technical.assesment.domain.repositories.SearchAndDataRepository
import javax.inject.Inject

class GetNearbyPlacesUseCaseImpl
@Inject constructor(
    private val searchAndDataRepository: SearchAndDataRepository
) : GetNearbyPlacesUseCase {

    override suspend fun getNearbyPlaces(queryParams: PlacesQueryParams): List<Place> {
        return searchAndDataRepository.getNearbyPlaces(queryParams)
    }
}