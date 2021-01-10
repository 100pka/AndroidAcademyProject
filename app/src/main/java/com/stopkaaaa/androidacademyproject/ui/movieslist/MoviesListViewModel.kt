package com.stopkaaaa.androidacademyproject.ui.movieslist

import androidx.lifecycle.*
import com.stopkaaaa.androidacademyproject.data.models.Movie
import kotlinx.coroutines.launch

import com.stopkaaaa.androidacademyproject.data.net.RetrofitClient


class MoviesListViewModel : ViewModel() {

    private val _mutableMoviesList = MutableLiveData<List<Movie>>(emptyList())
    private val _mutableLoadingState = MutableLiveData(Status.DONE)

    val moviesList: LiveData<List<Movie>> get() = _mutableMoviesList
    val loadingState: LiveData<Status> get() = _mutableLoadingState

    fun load() {
        viewModelScope.launch {
            _mutableLoadingState.value = Status.FIRST_LOADING

            val moviesResponse = RetrofitClient.getPopularMovies()

            val movies = List(moviesResponse.moviesIdList.size) {
                RetrofitClient.getMovieById(moviesResponse.moviesIdList[it].id)
            }

            _mutableMoviesList.value = movies

            _mutableLoadingState.value = Status.DONE
        }
    }
}

enum class Status { FIRST_LOADING, DONE }