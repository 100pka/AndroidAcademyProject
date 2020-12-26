package com.stopkaaaa.androidacademyproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stopkaaaa.androidacademyproject.adapters.ActorListAdapter
import com.stopkaaaa.androidacademyproject.adapters.ActorListItemDecorator
import com.stopkaaaa.androidacademyproject.adapters.MovieListItemDecoration
import com.stopkaaaa.androidacademyproject.data.models.Movie
import com.stopkaaaa.androidacademyproject.databinding.FragmentMoviesDetailsBinding
import com.stopkaaaa.androidacademyproject.domain.MoviesDataSource

private const val MOVIE_KEY = "Movie"

class FragmentMoviesDetails : Fragment() {


    private var _binding: FragmentMoviesDetailsBinding? = null
    private val binding get() = _binding!!
    private var listenerMovie: MovieClickListener? = null
    lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle: Bundle? = this.arguments
        if (bundle != null) {
            val movieId = bundle.getInt(MOVIE_KEY)
            movie = MoviesDataSource().getMovies().first { it.id == movieId }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            listenerMovie?.backPressed()
        }
        bindMovie()

        binding.actorsRv.addItemDecoration(
            ActorListItemDecorator(
                resources.getDimension(R.dimen.margin_8).toInt())
        )

        val adapter: ActorListAdapter = ActorListAdapter()
        movie?.actorsList?.let { adapter.bindActors(it) }
        binding.actorsRv.adapter = adapter
    }

    private fun bindMovie() {
        binding.movieTitle.text = movie.title
        binding.genre.text = movie.genre
        binding.ageLimit.text = binding.root.resources.getString(R.string.age_limit, movie?.ageLimit)
        movie.posterBig.run { binding.backgroundPoster.setImageResource(this) }
        binding.reviewsCount.text = binding.root.resources.getString(R.string.reviews, movie?.reviewsCount)
        binding.rating.rating = (movie.rating.toFloat() ?: 0) as Float
        binding.storylineBody.text = movie.storyLine
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity is MovieClickListener) {
            this.listenerMovie = activity as MovieClickListener
        }
        else {
            throw IllegalArgumentException("Activity must implement MovieClickListener")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        listenerMovie = null
    }

    companion object {
        fun newInstance(movieId: Int): FragmentMoviesDetails {
            return FragmentMoviesDetails().apply {
                arguments = Bundle().apply {
                    putInt(MOVIE_KEY, movieId)
                }
            }
        }
    }

}