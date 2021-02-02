package com.stopkaaaa.androidacademyproject.ui.movieslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.stopkaaaa.androidacademyproject.R

import com.stopkaaaa.androidacademyproject.adapters.MovieListItemDecoration
import com.stopkaaaa.androidacademyproject.adapters.MoviesPagedListAdapter
import com.stopkaaaa.androidacademyproject.data.models.Movie
import com.stopkaaaa.androidacademyproject.data.paging.PaginationState
import com.stopkaaaa.androidacademyproject.databinding.FragmentMoviesListBinding
import com.stopkaaaa.androidacademyproject.ui.MovieClickListener


class FragmentMoviesList : Fragment() {

    lateinit var viewModel: MoviesListPagedViewModel

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!
    private var listenerMovie: MovieClickListener? = null

    private lateinit var moviesAdapter: MoviesPagedListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MoviesListPagedViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.moviesPagedLiveData.observe(this.viewLifecycleOwner, {
            pagedList -> moviesAdapter.submitList(pagedList)
        })
        viewModel.paginationState.observe(this.viewLifecycleOwner, this::updatePaginationState)

    }

    private fun updatePaginationState(state: PaginationState) {
        when (state) {
            PaginationState.DONE -> {
                binding.progressBar.isVisible = false
                binding.movieListRv.isVisible = true
                binding.progressBarBottom.isVisible = false
            }
            PaginationState.EMPTY -> {
                binding.progressBar.isVisible = false
                binding.movieListRv.isVisible = false
                binding.progressBarBottom.isVisible = false
            }
            PaginationState.LOADING_INITIAL -> {
                binding.progressBar.isVisible = true
                binding.movieListRv.isVisible = false
                binding.progressBarBottom.isVisible = false
            }
            PaginationState.LOADING_AFTER -> {
                binding.progressBar.isVisible = false
                binding.movieListRv.isVisible = true
                binding.progressBarBottom.isVisible = true
            }
            PaginationState.CONNECTION_LOST -> {
                binding.progressBar.isVisible = false
                binding.movieListRv.isVisible = true
                binding.progressBarBottom.isVisible = false
                Toast.makeText(this.context, "Lost connection", Toast.LENGTH_LONG).show()
            }
            PaginationState.ERROR -> {
                binding.progressBar.isVisible = false
                binding.movieListRv.isVisible = false
                binding.progressBarBottom.isVisible = false
                Toast.makeText(this.context, "Something goes wrong", Toast.LENGTH_LONG).show()
            }
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

    private fun setupRecyclerView() {
        listenerMovie?.let {
            moviesAdapter = MoviesPagedListAdapter(it)
        }
        binding.movieListRv.apply {
            adapter = moviesAdapter
            addItemDecoration(
                MovieListItemDecoration(resources.getDimension(R.dimen.margin_6).toInt())
            )
        }
    }
}
