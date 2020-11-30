package com.stopkaaaa.androidacademyproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlin.math.log

class MainActivity : AppCompatActivity(), ClickListener {

    private val moviesList = FragmentMoviesList().apply {
        setListener(this@MainActivity)
    }
    private val moviesDetails = FragmentMoviesDetails().apply {
        setListener(this@MainActivity)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .apply {
                    add(R.id.fragments_container, moviesList)
                    commit()
                }
        }

    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size > 1) {
            backPressed()
        } else {
            super.onBackPressed()
        }
    }

    override fun backPressed() {
        supportFragmentManager.beginTransaction().apply {
            remove(moviesDetails)
            show(moviesList)
            commit()
        }
        Log.i("MainActivity", "backStackEntryCount: ${supportFragmentManager.fragments.size}")
    }

    override fun movieClicked() {
        supportFragmentManager.beginTransaction()
            .apply {
                hide(moviesList)
                add(R.id.fragments_container, moviesDetails)
                commit()
            }
        Log.i("MainActivity", "backStackEntryCount: ${supportFragmentManager.fragments.size}")
    }
}
