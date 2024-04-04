package jk.labs.dev.betsson.group.technical.assesment.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_places")
data class FavoritePlaceEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int?,
    @ColumnInfo("fsq_id") val fsqId: String,
    @ColumnInfo("is_favorite") val isFavorite: Boolean
) {
    constructor(fsqId: String, isFavorite: Boolean) : this(null, fsqId, isFavorite)
}
