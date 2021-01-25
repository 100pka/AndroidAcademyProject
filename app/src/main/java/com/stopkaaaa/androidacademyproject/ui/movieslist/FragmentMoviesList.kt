package com.stopkaaaa.androidacademyproject.ui.movieslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

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
                binding.progressBar.visibility = View.GONE
                binding.movieListRv.visibility = View.VISIBLE
            }
            PaginationState.EMPTY -> {
                binding.progressBar.visibility = View.GONE
                binding.movieListRv.visibility = View.INVISIBLE
            }
            PaginationState.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.movieListRv.visibility = View.INVISIBLE
            }
            PaginationState.ERROR -> {
                binding.progressBar.visibility = View.GONE
                binding.movieListRv.visibility = View.VISIBLE
                Toast.makeText(this.context, "No Connection", Toast.LENGTH_LONG).show()
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
        if (listenerMovie != null) {
            moviesAdapter = MoviesPagedListAdapter(listenerMovie!!)
        } else {
            throw IllegalArgumentException("No listener")
        }

        binding.movieListRv.apply {
            adapter = moviesAdapter
            addItemDecoration(
                MovieListItemDecoration(resources.getDimension(R.dimen.margin_6).toInt())
            )
        }
    }
}
