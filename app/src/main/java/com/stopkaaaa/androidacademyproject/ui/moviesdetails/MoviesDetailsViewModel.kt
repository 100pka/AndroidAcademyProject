package com.stopkaaaa.androidacademyproject.ui.moviesdetails

import androidx.lifecycle.*
import com.stopkaaaa.androidacademyproject.data.models.Actor
import com.stopkaaaa.androidacademyproject.data.models.Movie
import com.stopkaaaa.androidacademyproject.data.net.RetrofitClient
import kotlinx.coroutines.launch

class MoviesDetailsViewModel : ViewModel() {

    private val _mutableLoadingState = MutableLiveData<Boolean>(false)
    private val _mutableCurrentMovie = MutableLiveData<Movie>()
    private val _mutableActorsList = MutableLiveData<List<Actor>>(emptyList())

    val loadingState: LiveData<Boolean> get() = _mutableLoadingState
    val currentMovie: LiveData<Movie> get() = _mutableCurrentMovie
    val actorsList: LiveData<List<Actor>> get() = _mutableActorsList

    fun loadMovieById(id: Int) {
        viewModelScope.launch {

            _mutableLoadingState.value = true

            val movie = RetrofitClient.getMovieById(id)
            _mutableCurrentMovie.value = movie

            val actors = RetrofitClient.getMovieActorsById(id).actors
            _mutableActorsList.value = actors

            _mutableLoadingState.value = false

        }
    }
}