package com.stopkaaaa.androidacademyproject.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActorListResponse(
    @SerialName("id")
    val id: Int,

    @SerialName("cast")
    val actors: List<Actor>
)