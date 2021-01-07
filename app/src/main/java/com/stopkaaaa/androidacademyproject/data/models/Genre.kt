package com.stopkaaaa.androidacademyproject.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Genre(val id: Long, val name: String) {
    override fun toString(): String {
        return name
    }
}