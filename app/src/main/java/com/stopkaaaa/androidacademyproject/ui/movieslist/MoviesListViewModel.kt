package com.stopkaaaa.androidacademyproject.ui.movieslist

import androidx.lifecycle.*
import com.stopkaaaa.androidacademyproject.data.models.Movie
import kotlinx.coroutines.launch

import com.stopkaaaa.androidacademyproject.data.net.RetrofitClient


class MoviesListViewModel : ViewModel() {

    private val _mutableMoviesList = MutableLiveData<List<Movie>>(emptyList())
    private val _mutableLoadingState = MutableLiveData<Boolean>(false)

    val moviesList: LiveData<List<Movie>> get() = _mutableMoviesList
    val loadingState: LiveData<Boolean> get() = _mutableLoadingState

    fun load() {
        viewModelScope.launch {
            _mutableLoadingState.value = true

            val moviesId = RetrofitClient.getPopularMovies().moviesIdList

            val movies = List(moviesId.size) {
                RetrofitClient.getMovieById(moviesId[it].id)
            }

            _mutableMoviesList.value = movies

            _mutableLoadingState.value = false
        }
    }
}