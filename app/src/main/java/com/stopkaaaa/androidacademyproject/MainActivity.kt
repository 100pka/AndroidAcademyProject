package com.stopkaaaa.androidacademyproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.stopkaaaa.androidacademyproject.data.models.Movie

class MainActivity : AppCompatActivity(), MovieClickListener {

    private val moviesList = FragmentMoviesList()


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
        if (supportFragmentManager.backStackEntryCount > 1) {
            backPressed()
        } else {
            super.onBackPressed()
        }
    }

    override fun backPressed() {
        supportFragmentManager.popBackStack()
        Log.i("MainActivity", "backStackEntryCount: ${supportFragmentManager.backStackEntryCount}")
    }

    override fun movieClicked(movieIndex: Int) {
        supportFragmentManager.beginTransaction()
            .apply {
                add(R.id.fragments_container, FragmentMoviesDetails.newInstance(movieIndex))
                addToBackStack("movieDetails")
                commit()
            }
        Log.i("MainActivity", "backStackEntryCount: ${supportFragmentManager.backStackEntryCount}")
    }
}
