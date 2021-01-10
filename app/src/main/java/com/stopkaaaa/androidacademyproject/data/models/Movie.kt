package com.stopkaaaa.androidacademyproject.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(

    @SerialName("adult")
    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdrop: String?,

    @SerialName("genres")
    val genres: List<Genre>,

    @SerialName("id")
    val id: Int,

    @SerialName("overview")
    val overview: String,

    @SerialName("poster_path")
    val poster: String?,

    @SerialName("runtime")
    val runtime: Int?,

    @SerialName("title")
    val title: String,

    @SerialName("vote_average")
    val ratings: Double,

    @SerialName("vote_count")
    val votes: Int
)





