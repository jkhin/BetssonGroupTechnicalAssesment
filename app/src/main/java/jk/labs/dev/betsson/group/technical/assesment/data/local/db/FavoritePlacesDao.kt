package jk.labs.dev.betsson.group.technical.assesment.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jk.labs.dev.betsson.group.technical.assesment.data.entities.FavoritePlaceEntity

@Dao
interface FavoritePlacesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoritePlaces(vararg favoritePlaces: FavoritePlaceEntity)

    @Query("SELECT * FROM favorite_places")
    fun getFavoritePlace(): List<FavoritePlaceEntity>

}