package com.stopkaaaa.androidacademyproject.data.db

import android.provider.BaseColumns

object MovieDbContract {
    const val DATABASE_NAME = "Movies.db"

    object Movies{
        const val TABLE_NAME = "movies"

        const val COLUMN_NAME_ID = BaseColumns._ID
    }
}