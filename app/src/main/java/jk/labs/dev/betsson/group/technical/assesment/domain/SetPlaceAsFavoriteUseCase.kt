package jk.labs.dev.betsson.group.technical.assesment.domain

interface SetPlaceAsFavoriteUseCase {
    suspend fun setPlaceAsFavorite(fsqId: String, isFavorite: Boolean)
}