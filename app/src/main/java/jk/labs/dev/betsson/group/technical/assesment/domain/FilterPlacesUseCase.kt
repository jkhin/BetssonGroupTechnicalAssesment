package jk.labs.dev.betsson.group.technical.assesment.domain

import jk.labs.dev.betsson.group.technical.assesment.domain.entities.FilterQueryParams
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.Place

interface FilterPlacesUseCase {
    suspend fun filter(query: FilterQueryParams): List<Place>
}