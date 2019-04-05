package pl.daftacademy.androidlevelup.view.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_movies.*
import pl.daftacademy.androidlevelup.R
import pl.daftacademy.androidlevelup.view.viewmodel.MoviesViewModel


const val GENRE_EXTRA = "GENRE_EXTRA"

class MoviesFragment : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(this)[MoviesViewModel::class.java] }
    private val adapter = MoviesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_movies, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val genre = arguments?.getString(GENRE_EXTRA) ?: throw IllegalStateException("Required genre argument")
        recyclerView.adapter = adapter
        if (genre.isEmpty()) {
            adapter.items = viewModel.getMovies()
        }else{
            adapter.items = viewModel.getMoviesOfGenre(genre)
        }
    }

    companion object {
        fun create(genre: String): MoviesFragment {
            val fragment = MoviesFragment()
            val args = Bundle()
            args.putString(GENRE_EXTRA, genre)
            fragment.arguments = args
            return fragment
        }
    }
}
