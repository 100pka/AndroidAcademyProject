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
import com.stopkaaaa.androidacademyproject.data.models.loadMovies
import com.stopkaaaa.androidacademyproject.databinding.FragmentMoviesDetailsBinding
import com.stopkaaaa.androidacademyproject.domain.MoviesDataSource
import kotlinx.coroutines.launch

class FragmentMoviesDetails : Fragment() {


    private var _binding: FragmentMoviesDetailsBinding? = null
    private val binding get() = _binding!!
    private var listenerMovie: MovieClickListener? = null
    lateinit var movie: Movie

    private var moviesList: List<Movie>? = null

    init {
        lifecycleScope.launch{
            whenCreated {
                moviesList = context?.let { loadMovies(it) }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle: Bundle? = this.arguments
        if (bundle != null) {
            movie = moviesList?.get(bundle.getInt("Movie"))!!
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
        adapter.bindActors(movie.actors)
        binding.actorsRv.adapter = adapter
    }

    private fun bindMovie() {
        binding.movieTitle.text = movie.title
        binding.genre.text = movie.genres.toString()
        Glide.with(binding.root.context)
            .load(movie.backdrop)
            .into(binding.backgroundPoster)
        binding.reviewsCount.text = "${movie.votes} reviews"
        binding.rating.rating = movie.ratings
        binding.storylineBody.text = movie.overview
        if (movie.adult) {
            binding.ageLimit.text = "16+"
        } else {
            binding.ageLimit.text = "13+"
        }
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
                    putInt("Movie", movieIndex)
                }
            }
        }
    }

}