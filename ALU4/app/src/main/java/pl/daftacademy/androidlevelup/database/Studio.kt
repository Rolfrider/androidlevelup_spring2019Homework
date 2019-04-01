package pl.daftacademy.androidlevelup.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.daftacademy.androidlevelup.entity.Movie

@Entity
data class Studio(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String
){
    companion object {
        fun fromEntity(entity: Movie) = Studio(0, entity.studio!!)
    }
}
