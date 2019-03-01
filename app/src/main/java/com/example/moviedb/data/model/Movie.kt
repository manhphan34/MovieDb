package com.example.moviedb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
class Movie {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int = 0

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val voteAverage: Double? = null

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String? = null

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val posterPath: String? = null

    @ColumnInfo(name = "favorite")
    val favorite: Int? = null
}