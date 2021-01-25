package com.stopkaaaa.androidacademyproject.data.db

import androidx.room.TypeConverter
import com.stopkaaaa.androidacademyproject.data.models.Genre
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*

class GenreConverter {

    private val json = Json.Default

    @TypeConverter
    fun fromGenreList(value: List<Genre>?): String {
        if (value == null) {
            return json.encodeToString(emptyList<Genre>())
        }
        return json.encodeToString(value)
    }

    @TypeConverter
    fun toGenreList(value: String?): List<Genre> {
        if (value == null) {
            return Collections.emptyList()
        }
        return json.decodeFromString(value)
    }
}