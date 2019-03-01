package com.example.moviedb.data.model

import com.google.gson.annotations.SerializedName

class MovieResponse {
    @SerializedName("page")
    val page: Int? = null

    @SerializedName("total_pages")
    val totalPages: Int? = null

    @SerializedName("results")
    val results: List<Movie>? = null
}