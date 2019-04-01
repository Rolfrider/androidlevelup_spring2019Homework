package pl.daftacademy.androidlevelup.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
abstract class MovieDao {


    @Insert
    abstract fun addMovie(movies: Collection<Movie>)

    @Insert
    abstract fun addStudio(studio: Studio): Long

    fun addMovieWithStudio(studio: Studio ,movies: Collection<Movie>){
        val id = getStudioIdByName(studio.name) ?: addStudio(studio).toInt()
        addMovie(movies.map { Movie(0, it.title, it.year, it.genres, id)})
    }

    @Query("SELECT * FROM movie")
    abstract fun get(): List<Movie>


    @Query("SELECT id FROM studio WHERE name = :studioName")
    abstract fun getStudioIdByName(studioName: String): Int?


    @Query("SELECT * FROM studio")
    abstract fun getStudioWithMovies(): List<StudioWithMovies>
}
