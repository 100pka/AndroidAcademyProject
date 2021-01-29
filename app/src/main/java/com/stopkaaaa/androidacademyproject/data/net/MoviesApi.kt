package com.stopkaaaa.androidacademyproject.data.net

import com.stopkaaaa.androidacademyproject.data.models.ActorListResponse
import com.stopkaaaa.androidacademyproject.data.models.Movie
import com.stopkaaaa.androidacademyproject.data.models.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {
    @GET("movie/popular")
    suspend fun getPopularMoviesByPage(@Query("page") page: Int): Response<MovieListResponse>

    @GET("movie/{movieId}")
    suspend fun getMovieById(@Path("movieId") movieId: Int): Movie

    @GET("movie/{movieId}/credits")
    suspend fun getMovieActorsById(@Path("movieId") movieId: Int): ActorListResponse
}