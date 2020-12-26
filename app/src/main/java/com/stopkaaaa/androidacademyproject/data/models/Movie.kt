package com.stopkaaaa.androidacademyproject.data.models

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster: String,
    val backdrop: String,
    val ratings: Float,
    val votes: Int,
    val adult: Boolean,
    val runtime: Int,
    val genres: List<Genre>,
    val actors: List<Actor>
)
