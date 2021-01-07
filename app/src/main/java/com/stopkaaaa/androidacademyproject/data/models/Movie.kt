package com.stopkaaaa.androidacademyproject.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(

    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdrop: String,

    val genres: List<Genre>,

    val id: Long,

    val overview: String,

    @SerialName("poster_path")
    val poster: String,

    val runtime: Long = 0,

    val title: String,

    @SerialName("vote_average")
    val ratings: Double,

    @SerialName("vote_count")
    val votes: Long
)





