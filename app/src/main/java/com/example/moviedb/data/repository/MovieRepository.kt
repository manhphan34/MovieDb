package com.example.moviedb.data.repository

import com.example.moviedb.data.MovieDataSource
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.model.MovieResponse
import com.example.moviedb.data.remote.MovieRemoteDataSource
import io.reactivex.Single

class MovieRepository(val remote: MovieDataSource.Remote) : MovieDataSource.Remote {

    override fun getMovieById(id: Int): Single<Movie> {
        return remote.getMovieById(id)
    }

    override fun getMovies(page: Int): Single<List<Movie>> {
        return remote.getMovies(page)
    }
}