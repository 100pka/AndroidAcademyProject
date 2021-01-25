package com.stopkaaaa.androidacademyproject

import android.app.Application
import com.stopkaaaa.androidacademyproject.data.db.MovieDatabase

lateinit var db: MovieDatabase

class App : Application() {
    companion object {
        lateinit var INSTANCE: App
    }

    init {
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()
        db = MovieDatabase.create(this)
        INSTANCE = this
    }
}