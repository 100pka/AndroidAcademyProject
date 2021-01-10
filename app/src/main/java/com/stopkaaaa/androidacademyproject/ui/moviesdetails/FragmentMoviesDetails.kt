package com.stopkaaaa.androidacademyproject.ui.moviesdetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load

import com.stopkaaaa.androidacademyproject.BuildConfig
import com.stopkaaaa.androidacademyproject.R
import com.stopkaaaa.androidacademyproject.adapters.ActorListAdapter
import com.stopkaaaa.androidacademyproject.adapters.ActorListItemDecorator
import com.stopkaaaa.androidacademyproject.data.models.Actor
import com.stopkaaaa.androidacademyproject.data.models.Movie
import com.stopkaaaa.androidacademyproject.databinding.FragmentMoviesDetailsBinding
import com.stopkaaaa.androidacademyproject.ui.MovieClickListener

const val MOVIE_TAG = "Movie"

class FragmentMoviesDetails : Fragment() {

    lateinit var viewModel: MoviesDetailsViewModel

    private var _binding: FragmentMoviesDetailsBinding? = null
    private val binding get() = _binding!!
    private var listenerMovie: MovieClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MoviesDetailsViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            listenerMovie?.backPressed()
        }

        binding.actorsRv.addItemDecoration(
            ActorListItemDecorator(
                resources.getDimension(R.dimen.margin_8).toInt()
            )
        )

        viewModel.currentMovie.observe(this.viewLifecycleOwner, this::bindMovie)
        viewModel.actorsList.observe(this.viewLifecycleOwner, this::bindActors)
        viewModel.loadingState.observe(this.viewLifecycleOwner, this::setLoading)

        val bundle: Bundle? = this.arguments
        bundle?.getInt(MOVIE_TAG)?.let { viewModel.loadMovieById(it) }

    }

    private fun bindMovie(movie: Movie) {
        movie.run {
            binding.movieTitle.text = title
            binding.genre.text = genres.toString()
                .subSequence(1, genres.toString().length - 1)
            binding.backgroundPoster.load(BuildConfig.TMDB_IMAGE_URL + movie.backdrop) {
                placeholder(R.drawable.backdrop_placeholder)
            }
            binding.reviewsCount.text = resources.getString(R.string.reviews, votes)
            binding.rating.rating = ratings.div(2).toFloat()
            binding.storylineBody.text = overview
            if (adult) {
                binding.ageLimit.text = resources.getString(R.string.age_adult)
            } else {
                binding.ageLimit.text = resources.getString(R.string.age_non_adult)
            }
        }
    }

    private fun bindActors(actorsList: List<Actor>) {
        if (actorsList.isEmpty()) {
            binding.castTitle.visibility = View.INVISIBLE
        } else {
            binding.castTitle.visibility = View.VISIBLE
            val adapter = ActorListAdapter()
            adapter.bindActors(actorsList)
            binding.actorsRv.adapter = adapter
        }
    }

    private fun setLoading(loading: Boolean) {
        binding.movieDetailsProgressBar.isGone = !loading
        binding.movieDetailsContainer.isGone = loading
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity is MovieClickListener) {
            this.listenerMovie = activity as MovieClickListener
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
        fun newInstance(movieID: Int): FragmentMoviesDetails {
            return FragmentMoviesDetails().apply {
                arguments = Bundle().apply {
                    putInt(MOVIE_TAG, movieID)
                }
            }
        }
    }
}