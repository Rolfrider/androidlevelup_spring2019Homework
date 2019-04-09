package pl.daftacademy.androidlevelup.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pl.daftacademy.androidlevelup.data.MovieDao
import pl.daftacademy.androidlevelup.data.MovieFileDao
import pl.daftacademy.androidlevelup.entity.Movie

class MoviesViewModel(application: Application): AndroidViewModel(application) {
    private val movieDao: MovieDao = MovieFileDao(getApplication())

    fun getMovies() = movieDao.getAllMovies()

    fun getMoviesOfGenre(genre: String?): List<Movie> {
        return if (genre == null){
            getMovies()
        }else {
            getMovies().filter { it.genres.contains(genre)}
        }
    }
}
