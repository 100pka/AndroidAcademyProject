package com.stopkaaaa.androidacademyproject.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.stopkaaaa.androidacademyproject.data.db.MovieDbContract
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(
    tableName = MovieDbContract.Movies.TABLE_NAME,
    indices = [Index(MovieDbContract.Movies.COLUMN_NAME_ID)]
)
@Serializable
data class Movie(

    @SerialName("adult")
    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdrop: String?,

    @SerialName("genres")
    val genres: List<Genre>,

    @PrimaryKey
    @SerialName("id")
    @ColumnInfo(name = MovieDbContract.Movies.COLUMN_NAME_ID)
    val id: Int,

    @SerialName("overview")
    val overview: String,

    @SerialName("poster_path")
    val poster: String?,

    @SerialName("runtime")
    val runtime: Int?,

    @SerialName("title")
    val title: String,

    @SerialName("vote_average")
    val ratings: Double,

    @SerialName("vote_count")
    val votes: Int
)