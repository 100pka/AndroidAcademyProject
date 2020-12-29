package com.stopkaaaa.androidacademyproject.ui.movieslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isInvisible

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.stopkaaaa.androidacademyproject.R

import com.stopkaaaa.androidacademyproject.adapters.MovieListItemDecoration
import com.stopkaaaa.androidacademyproject.adapters.MovieListAdapter
import com.stopkaaaa.androidacademyproject.data.models.Movie
import com.stopkaaaa.androidacademyproject.databinding.FragmentMoviesListBinding
import com.stopkaaaa.androidacademyproject.ui.MovieClickListener


class FragmentMoviesList : Fragment() {

    lateinit var viewModel: MoviesListViewModel

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!
    private var listenerMovie: MovieClickListener? = null

    lateinit var moviesAdapter: MovieListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MoviesListViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.movieListRv.addItemDecoration(
            MovieListItemDecoration(
                resources.getDimension(R.dimen.margin_6).toInt()
            )
        )

        if (listenerMovie != null) {
            moviesAdapter = MovieListAdapter(listenerMovie!!)
        } else {
            throw IllegalArgumentException("No listener")
        }

        binding.movieListRv.adapter = moviesAdapter

        viewModel.moviesList.observe(this.viewLifecycleOwner, this::updateAdapter)
        viewModel.loadingState.observe(this.viewLifecycleOwner, this::setLoading)

        viewModel.load()
    }

    private fun setLoading(loading: Boolean) {
        binding.progressBar.isGone = !loading
        binding.movieListRv.isInvisible = loading
    }

    private fun updateAdapter(movies: List<Movie>) {
        moviesAdapter.bindMovies(movies)
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
}
