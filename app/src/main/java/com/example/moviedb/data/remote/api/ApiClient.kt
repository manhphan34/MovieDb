package com.example.moviedb.data.remote.api

import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {
    @GET("/3/movie/popular")
    fun getPopular(@Query("page") page: Int): Single<MovieResponse>

    @GET("/3/movie/{movie_id}")
    fun getMovie(@Path("movie_id") id: Int): Single<Movie>
}