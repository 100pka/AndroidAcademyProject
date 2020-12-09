package com.stopkaaaa.androidacademyproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import androidx.lifecycle.whenStarted
import com.bumptech.glide.Glide
import com.stopkaaaa.androidacademyproject.adapters.ActorListAdapter
import com.stopkaaaa.androidacademyproject.adapters.ActorListItemDecorator
import com.stopkaaaa.androidacademyproject.adapters.MovieListItemDecoration
import com.stopkaaaa.androidacademyproject.data.models.Movie
import com.stopkaaaa.androidacademyproject.data.models.getMovieById
import com.stopkaaaa.androidacademyproject.data.models.loadMovies
import com.stopkaaaa.androidacademyproject.databinding.FragmentMoviesDetailsBinding
import com.stopkaaaa.androidacademyproject.domain.MoviesDataSource
import kotlinx.coroutines.launch

const val MOVIE_TAG = "Movie"

class FragmentMoviesDetails : Fragment() {


    private var _binding: FragmentMoviesDetailsBinding? = null
    private val binding get() = _binding!!
    private var listenerMovie: MovieClickListener? = null
    private var movie: Movie? = null

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

        binding.actorsRv.addItemDecoration(
            ActorListItemDecorator(
                resources.getDimension(R.dimen.margin_8).toInt()
            )
        )

        val bundle: Bundle? = this.arguments
        lifecycleScope.launch {
            movie = context?.let { bundle?.getInt(MOVIE_TAG)?.let { it1 -> getMovieById(it, it1) } }
            bindMovie()
        }
    }

    private fun bindMovie() {
        binding.movieTitle.text = movie?.title ?: resources.getString(R.string.error_loading_title)
        binding.genre.text = movie?.genres.toString()
            .subSequence(1, movie?.genres.toString().length - 1)
        context?.let {
            Glide.with(it)
                .load(movie?.backdrop)
                .placeholder(R.drawable.backdrop_placeholder)
                .dontAnimate()
                .into(binding.backgroundPoster)
        }
        binding.reviewsCount.text = resources.getString(R.string.reviews, movie?.votes)
        binding.rating.rating = movie?.ratings?.div(2) ?: Float.MIN_VALUE
        binding.storylineBody.text = movie?.overview
        if (movie?.adult == true) {
            binding.ageLimit.text = resources.getString(R.string.age_adult)
        } else {
            binding.ageLimit.text = resources.getString(R.string.age_non_adult)
        }
        val adapter: ActorListAdapter = ActorListAdapter()
        movie?.let { adapter.bindActors(it.actors) }
        binding.actorsRv.adapter = adapter
        adapter.notifyDataSetChanged()
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
        fun newInstance(movieIndex: Int): FragmentMoviesDetails {
            return FragmentMoviesDetails().apply {
                arguments = Bundle().apply {
                    putInt(MOVIE_TAG, movieIndex)
                }
            }
        }
    }
}