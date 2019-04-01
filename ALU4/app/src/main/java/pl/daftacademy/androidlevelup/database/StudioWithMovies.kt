package pl.daftacademy.androidlevelup.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import pl.daftacademy.androidlevelup.entity.Movie as EntityMovie

@Entity
class StudioWithMovies {

    @Embedded
    var studio: Studio? = null

    @Relation(parentColumn = "id", entityColumn = "studioId")
    var movies: List<Movie>? = null

    fun toEntity(): List<EntityMovie> = if (movies != null && studio != null){
        movies!!.map { EntityMovie( it.title, it.year, it.genres.split(","), studio!!.name) }
    }else {
        emptyList()
    }
}
