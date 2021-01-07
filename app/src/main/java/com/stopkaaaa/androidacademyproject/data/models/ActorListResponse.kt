package com.stopkaaaa.androidacademyproject.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActorListResponse(
    val id: Long,

    @SerialName("cast")
    val actors: List<Actor>
)