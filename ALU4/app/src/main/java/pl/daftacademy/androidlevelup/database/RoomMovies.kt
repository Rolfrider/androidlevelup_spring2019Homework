package pl.daftacademy.androidlevelup.database

import pl.daftacademy.androidlevelup.entity.Movie
import pl.daftacademy.androidlevelup.entity.Movies
import pl.daftacademy.androidlevelup.database.Movie as DbMovie

class RoomMovies(private val movieDao: MovieDao) : Movies {

    override fun add(movies: Collection<Movie>) {
        movieDao.addMovieWithStudio(movies.filter { it.studio != null }
            .map(Studio.Companion::fromEntity).first(),
            movies.map(DbMovie.Companion::fromEntity))
    }

    override fun get(): List<Movie> {
        val studiosWithMovies = movieDao.getStudioWithMovies()
        var allMovies: List<Movie> = listOf()
        studiosWithMovies.forEach { allMovies += it.toEntity() }
        return allMovies
    }
}
