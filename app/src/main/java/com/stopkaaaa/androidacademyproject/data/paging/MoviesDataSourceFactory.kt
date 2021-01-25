package com.stopkaaaa.androidacademyproject.data.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.stopkaaaa.androidacademyproject.data.MovieRepository
import com.stopkaaaa.androidacademyproject.data.models.Movie


class MoviesDataSourceFactory(
    val repository: MovieRepository
) : DataSource.Factory<Int, Movie>() {

    private val _moviesDataSourceLiveData = MutableLiveData<MoviesDataSource>()
    val moviesDataSourceLiveData: LiveData<MoviesDataSource> get() = _moviesDataSourceLiveData

    override fun create(): DataSource<Int, Movie> {
        val moviesDataSource = MoviesDataSource(repository)
        _moviesDataSourceLiveData.postValue(moviesDataSource)
        return moviesDataSource
    }

    fun getSource() = moviesDataSourceLiveData.value
}