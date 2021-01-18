package com.stopkaaaa.androidacademyproject.ui.moviesdetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stopkaaaa.androidacademyproject.data.models.Movie
import com.stopkaaaa.androidacademyproject.data.models.getMovieById
import kotlinx.coroutines.launch

class MoviesDetailsViewModel(
    application: Application,
    private val movieId: Int
) : AndroidViewModel(application) {

    private val _mutableLoadingState = MutableLiveData(false)
    private val _mutableCurrentMovie = MutableLiveData<Movie>()

    val loadingState: LiveData<Boolean> get() = _mutableLoadingState
    val currentMovie: LiveData<Movie> get() = _mutableCurrentMovie

    init {
        viewModelScope.launch {

            _mutableLoadingState.value = true

            val movie = getMovieById(getApplication(), movieId)
            _mutableCurrentMovie.value = movie

            _mutableLoadingState.value = false

        }
    }

//    fun loadMovieById(id: Int) {
//        viewModelScope.launch {
//
//            _mutableLoadingState.value = true
//
//            val movie = getMovieById(getApplication(), id)
//            _mutableCurrentMovie.value = movie
//
//            _mutableLoadingState.value = false
//
//        }
//    }
}