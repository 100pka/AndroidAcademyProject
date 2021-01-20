package com.stopkaaaa.androidacademyproject.ui.movieslist


import android.app.Application
import androidx.lifecycle.*
import com.stopkaaaa.androidacademyproject.data.models.Movie
import kotlinx.coroutines.launch

import com.stopkaaaa.androidacademyproject.data.models.loadMovies

class MoviesListViewModel(application: Application) : AndroidViewModel(application) {

    private val _mutableMoviesList = MutableLiveData<List<Movie>>(emptyList())
    private val _mutableLoadingState = MutableLiveData(false)

    val moviesList: LiveData<List<Movie>> get() = _mutableMoviesList
    val loadingState: LiveData<Boolean> get() = _mutableLoadingState

    init {
        viewModelScope.launch {
            _mutableLoadingState.value = true

            val moviesList = loadMovies(getApplication())
            _mutableMoviesList.value = moviesList

            _mutableLoadingState.value = false
        }
    }
}