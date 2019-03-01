package com.example.moviedb.data.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class Movie {

    @SerializedName("id")
    val id: Int? = null

    @SerializedName("vote_average")
    val voteAverage: Double? = null

    @SerializedName("title")
    val title: String? = null

    @SerializedName("poster_path")
    val posterPath: String? = null
}