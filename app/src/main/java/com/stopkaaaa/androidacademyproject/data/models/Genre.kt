package com.stopkaaaa.androidacademyproject.data.models

data class Genre(val id: Int, val name: String) {
    override fun toString(): String {
        return name
    }
}