package jk.labs.dev.betsson.group.technical.assesment.data.local

import jk.labs.dev.betsson.group.technical.assesment.data.entities.FavoritePlaceEntity

interface FavoritePlaceLocalDataSource {
    suspend fun setPlaceAsFavorite(entity: FavoritePlaceEntity)
    suspend fun getFavoritePlace(): List<FavoritePlaceEntity>
}