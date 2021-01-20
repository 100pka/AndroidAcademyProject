package com.stopkaaaa.androidacademyproject.ui.moviesdetails

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MoviesDetailsViewModelFactory(
    private val application: Application,
    private val movieId: Int
) : ViewModelProvider.AndroidViewModelFactory(application) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MoviesDetailsViewModel::class.java -> MoviesDetailsViewModel(this.application, this.movieId)
            else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
        } as T
    }
}