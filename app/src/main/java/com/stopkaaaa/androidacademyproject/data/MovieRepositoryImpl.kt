package com.stopkaaaa.androidacademyproject.data

import androidx.lifecycle.LiveData
import com.stopkaaaa.androidacademyproject.data.models.Movie
import com.stopkaaaa.androidacademyproject.data.net.RetrofitClient
import com.stopkaaaa.androidacademyproject.db
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MovieRepositoryImpl : MovieRepository {

    private val movieDao = db.movieDao

    override suspend fun getPopularMoviesByPage(page: Int) = withContext(Dispatchers.IO) {
        RetrofitClient.moviesApi.getPopularMoviesByPage(page)
    }

    override suspend fun getMovieById(movieId: Int) = withContext(Dispatchers.IO) {
        RetrofitClient.moviesApi.getMovieById(movieId)
    }

    override suspend fun getMovieActorsById(movieId: Int) = withContext(Dispatchers.IO) {
        RetrofitClient.moviesApi.getMovieActorsById(movieId)
    }

    override suspend fun saveMovie(movie: Movie) = withContext(Dispatchers.IO){
        movieDao.insert(movie)
    }

    override suspend fun getSavedMovies(): List<Movie> = withContext(Dispatchers.IO){
        movieDao.getAll()
    }
}