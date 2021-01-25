package com.stopkaaaa.androidacademyproject.data.db

import androidx.room.*
import com.stopkaaaa.androidacademyproject.data.models.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie)

    @Query("select * from movies")
    fun getAll(): List<Movie>

    @Query("DELETE FROM movies WHERE _id = :id")
    fun delete(id: Int?)
}