package pl.daftacademy.androidlevelup.database

import pl.daftacademy.androidlevelup.entity.Movie
import pl.daftacademy.androidlevelup.entity.Movies
import pl.daftacademy.androidlevelup.database.Movie as DbMovie

class RoomMovies(private val movieDao: MovieDao) : Movies {

    override fun add(movies: Collection<Movie>) {
        movieDao.addMoviesWithStudios(  movies.groupBy({Studio.fromEntity(it)}, {DbMovie.fromEntity(it)}) )
    }

    override fun get(): List<Movie> {
        return movieDao.getStudioWithMovies().flatMap { it.toListOfMovies() }
    }
}
