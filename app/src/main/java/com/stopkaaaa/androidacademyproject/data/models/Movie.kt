package com.stopkaaaa.androidacademyproject.data.models

data class Movie(
    val id: Int,
    val title: String,
    val genre: String,
    val ageLimit: Int,
    val duration: Int,
    val reviewsCount: Int,
    val rating: Int,
    val isLiked: Boolean,
    val posterBig: Int,
    val posterSmall: Int,
    val storyLine: String,
    val actorsList: List<Actor>
)
