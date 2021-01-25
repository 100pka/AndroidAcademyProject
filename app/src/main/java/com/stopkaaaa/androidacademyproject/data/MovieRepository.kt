package com.stopkaaaa.androidacademyproject.data

import com.stopkaaaa.androidacademyproject.data.models.ActorListResponse
import com.stopkaaaa.androidacademyproject.data.models.Movie
import com.stopkaaaa.androidacademyproject.data.models.MovieListResponse
import retrofit2.Response

interface MovieRepository {

    suspend fun getPopularMoviesByPage(page: Int): Response<MovieListResponse>

    suspend fun getMovieById(movieId: Int): Movie

    suspend fun getMovieActorsById(movieId: Int): ActorListResponse

    suspend fun saveMovie(movie: Movie)

    suspend fun getSavedMovies(): List<Movie>

}