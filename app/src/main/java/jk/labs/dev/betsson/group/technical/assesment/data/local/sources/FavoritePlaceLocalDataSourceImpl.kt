package jk.labs.dev.betsson.group.technical.assesment.data.local.sources

import jk.labs.dev.betsson.group.technical.assesment.data.entities.FavoritePlaceEntity
import jk.labs.dev.betsson.group.technical.assesment.data.local.FavoritePlaceLocalDataSource
import jk.labs.dev.betsson.group.technical.assesment.data.local.db.FavoritePlacesDao
import javax.inject.Inject

class FavoritePlaceLocalDataSourceImpl
    @Inject constructor(
        private val dao: FavoritePlacesDao
    ): FavoritePlaceLocalDataSource {
    override suspend fun setPlaceAsFavorite(entity: FavoritePlaceEntity) {
        dao.insertFavoritePlaces(entity)
    }

    override suspend fun getFavoritePlace(): List<FavoritePlaceEntity> {
        return dao.getFavoritePlace()
    }
}