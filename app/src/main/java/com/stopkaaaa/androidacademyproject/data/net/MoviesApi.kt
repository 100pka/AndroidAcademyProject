package com.stopkaaaa.androidacademyproject.data.net

import com.stopkaaaa.androidacademyproject.data.models.ActorListResponse
import com.stopkaaaa.androidacademyproject.data.models.Movie
import com.stopkaaaa.androidacademyproject.data.models.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(): MovieListResponse

    @GET("movie/{movieId}")
    suspend fun getMovieById(@Path("movieId") movieId: Int): Movie

    @GET("movie/{movieId}/credits")
    suspend fun getMovieActorsById(@Path("movieId") movieId: Int): ActorListResponse
}