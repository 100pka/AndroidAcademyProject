package com.stopkaaaa.androidacademyproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope

import com.bumptech.glide.Glide
import com.stopkaaaa.androidacademyproject.adapters.ActorListAdapter
import com.stopkaaaa.androidacademyproject.adapters.ActorListItemDecorator
import com.stopkaaaa.androidacademyproject.data.models.Movie
import com.stopkaaaa.androidacademyproject.data.models.getMovieById
import com.stopkaaaa.androidacademyproject.databinding.FragmentMoviesDetailsBinding
import kotlinx.coroutines.launch

const val MOVIE_TAG = "Movie"

private const val MOVIE_KEY = "Movie"

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
                resources.getDimension(R.dimen.margin_8).toInt())
        )

        val bundle: Bundle? = this.arguments
        lifecycleScope.launch {
            movie = context?.let { bundle?.getInt(MOVIE_TAG)?.let { it1 -> getMovieById(it, it1) } }
            bindMovie()
        }
    }

    private fun bindMovie() {
        movie?.let {
            binding.movieTitle.text = resources.getString(R.string.error_loading_title)
            binding.genre.text = it.genres.toString()
                .subSequence(1, it.genres.toString().length - 1)
            context?.let { _context ->
                Glide.with(_context)
                    .load(movie?.backdrop)
                    .placeholder(R.drawable.backdrop_placeholder)
                    .dontAnimate()
                    .into(binding.backgroundPoster)
            }
            binding.reviewsCount.text = resources.getString(R.string.reviews, it.votes)
            binding.rating.rating = it.ratings.div(2) ?: Float.MIN_VALUE
            binding.storylineBody.text = it.overview
            if (it.adult) {
                binding.ageLimit.text = resources.getString(R.string.age_adult)
            } else {
                binding.ageLimit.text = resources.getString(R.string.age_non_adult)
            }

            if (it.actors.isEmpty()) {
                binding.castTitle.visibility = View.INVISIBLE
            }
            else {
                binding.castTitle.visibility = View.VISIBLE
                val adapter = ActorListAdapter()
                adapter.bindActors(it.actors)
                binding.actorsRv.adapter = adapter
            }
        }
        if (movie == null) {
            Toast.makeText(
                context,
                getString(R.string.error_show_movie_details),
                Toast.LENGTH_SHORT
            ).show()
        }
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