package com.stopkaaaa.androidacademyproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope

import com.stopkaaaa.androidacademyproject.adapters.MovieListItemDecoration
import com.stopkaaaa.androidacademyproject.adapters.MovieListAdapter
import com.stopkaaaa.androidacademyproject.data.models.loadMovies
import com.stopkaaaa.androidacademyproject.databinding.FragmentMoviesListBinding
import kotlinx.coroutines.*


class FragmentMoviesList : Fragment() {

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!
    private var listenerMovie: MovieClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
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

        lifecycleScope.launch {
            val getMoviesTask = async {
                context?.let { loadMovies(it) }
            }
            val moviesList = getMoviesTask.await()
            adapter?.bindMovies(moviesList)
            binding.movieListRv.adapter = adapter
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
