package jk.labs.dev.betsson.group.technical.assesment.domain.usecases

import jk.labs.dev.betsson.group.technical.assesment.domain.SetPlaceAsFavoriteUseCase
import jk.labs.dev.betsson.group.technical.assesment.domain.repositories.FavoritePlaceRepository
import javax.inject.Inject

class SetPlaceAsFavoriteUseCaseImpl
    @Inject constructor(
        private val favoritePlaceRepository: FavoritePlaceRepository
    ): SetPlaceAsFavoriteUseCase {
    override suspend fun setPlaceAsFavorite(fsqId: String, isFavorite: Boolean) {
        favoritePlaceRepository.setPlaceAsFavorite(fsqId = fsqId, isFavorite = isFavorite)
    }
}