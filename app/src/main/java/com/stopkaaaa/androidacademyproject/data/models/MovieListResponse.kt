package com.stopkaaaa.androidacademyproject.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieListResponse (
    @SerialName("page")
    val page: Long,

    @SerialName("results")
    val moviesIdList: List<MovieId>,

    @SerialName("total_pages")
    val totalPages: Long,

    @SerialName("total_results")
    val totalResults: Long
)


