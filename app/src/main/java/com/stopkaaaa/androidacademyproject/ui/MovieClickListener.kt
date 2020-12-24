package com.stopkaaaa.androidacademyproject.ui

import com.stopkaaaa.androidacademyproject.data.models.Movie

interface MovieClickListener {
    fun movieClicked(movieIndex: Int)
    fun backPressed()
}