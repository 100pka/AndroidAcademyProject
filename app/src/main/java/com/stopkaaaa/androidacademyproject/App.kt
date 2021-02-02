package com.stopkaaaa.androidacademyproject

import android.app.Application
import com.stopkaaaa.androidacademyproject.data.db.MovieDatabase
import com.stopkaaaa.androidacademyproject.data.net.NetworkConnectionInterceptor

lateinit var db: MovieDatabase
lateinit var networkConnectionInterceptor: NetworkConnectionInterceptor

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
        networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        INSTANCE = this
    }
}