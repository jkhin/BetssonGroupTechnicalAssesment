package jk.labs.dev.betsson.group.technical.assesment.domain.usecases

import jk.labs.dev.betsson.group.technical.assesment.domain.FilterPlacesUseCase
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.FilterQueryParams
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.Place
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PlacesQueryParams
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PricingScale
import jk.labs.dev.betsson.group.technical.assesment.domain.repositories.SearchAndDataRepository
import javax.inject.Inject

class FilterPlacesUseCaseImpl
@Inject constructor(
    private val searchAndDataRepository: SearchAndDataRepository
) : FilterPlacesUseCase {
    override suspend fun filter(query: FilterQueryParams): List<Place> {
        val queryParams = PlacesQueryParams(
            isOpenNow = query.isOpen,
            minPrice = query.pricingScale?: PricingScale.MOST_AFFORDABLE,
            maxPrice = query.pricingScale?: PricingScale.MOST_EXPENSIVE,
        )

        return searchAndDataRepository.getNearbyPlaces(queryParams)
    }
}