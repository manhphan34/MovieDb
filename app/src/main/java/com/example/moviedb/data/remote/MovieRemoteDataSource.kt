package com.example.moviedb.data.remote

import com.example.moviedb.data.MovieDataSource
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.model.MovieResponse
import com.example.moviedb.data.remote.api.ApiClient
import io.reactivex.Single

class MovieRemoteDataSource(val apiClient: ApiClient) : MovieDataSource.Remote {

    override fun getMovies(page: Int): Single<List<Movie>> {
        return apiClient.getPopular(page).map {
            it.results
        }
    }

    override fun getMovieById(id: Int): Single<Movie> {
        return apiClient.getMovie(id)
    }
}