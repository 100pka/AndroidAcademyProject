package com.stopkaaaa.androidacademyproject.ui.movieslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.stopkaaaa.androidacademyproject.R

import com.stopkaaaa.androidacademyproject.adapters.MovieListItemDecoration
import com.stopkaaaa.androidacademyproject.adapters.MovieListAdapter
import com.stopkaaaa.androidacademyproject.data.models.loadMovies
import com.stopkaaaa.androidacademyproject.databinding.FragmentMoviesListBinding
import com.stopkaaaa.androidacademyproject.ui.MovieClickListener
import kotlinx.coroutines.*


class FragmentMoviesList : Fragment() {

    lateinit var viewModel: MoviesListViewModel

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!
    private var listenerMovie: MovieClickListener? = null

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
        val adapter = listenerMovie?.let { MovieListAdapter(it) }
        binding.movieListRv.adapter = adapter

        lifecycleScope.launch {
            val moviesList = context?.let { loadMovies(it) }
            adapter?.bindMovies(moviesList)
            binding.progressBar.visibility = View.GONE
            binding.movieListRv.visibility = View.VISIBLE
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
}
