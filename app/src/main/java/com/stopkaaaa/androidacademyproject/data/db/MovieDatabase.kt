package com.stopkaaaa.androidacademyproject.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.stopkaaaa.androidacademyproject.data.models.Movie

@Database(entities = [Movie::class], version = 1)
@TypeConverters(GenreConverter::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract val movieDao: MovieDao

    companion object {
        fun create(appContext: Context): MovieDatabase = Room.databaseBuilder(
            appContext,
            MovieDatabase::class.java,
            MovieDbContract.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}