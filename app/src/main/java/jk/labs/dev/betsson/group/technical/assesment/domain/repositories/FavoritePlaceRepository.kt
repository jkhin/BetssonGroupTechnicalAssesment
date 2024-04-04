package jk.labs.dev.betsson.group.technical.assesment.domain.repositories

interface FavoritePlaceRepository {
    suspend fun setPlaceAsFavorite(fsqId: String, isFavorite: Boolean)
}