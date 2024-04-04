package jk.labs.dev.betsson.group.technical.assesment.data.repositories

import jk.labs.dev.betsson.group.technical.assesment.data.entities.FavoritePlaceEntity
import jk.labs.dev.betsson.group.technical.assesment.data.local.FavoritePlaceLocalDataSource
import jk.labs.dev.betsson.group.technical.assesment.domain.repositories.FavoritePlaceRepository
import javax.inject.Inject

class FavoritePlaceRepositoryImpl
@Inject constructor(
    private val favoritePlaceLocalDataSource: FavoritePlaceLocalDataSource
) : FavoritePlaceRepository {
    override suspend fun setPlaceAsFavorite(fsqId: String, isFavorite: Boolean) {
        val entity = favoritePlaceLocalDataSource
            .getFavoritePlace()
            .firstOrNull {
                it.fsqId == fsqId
            }?: FavoritePlaceEntity(
            fsqId = fsqId,
            isFavorite = isFavorite
        )
        favoritePlaceLocalDataSource.setPlaceAsFavorite(entity)
    }

}