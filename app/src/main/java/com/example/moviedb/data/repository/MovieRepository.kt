package com.example.moviedb.data.repository

import com.example.moviedb.data.MovieDataSource
import com.example.moviedb.data.model.Movie
import io.reactivex.Flowable
import io.reactivex.Single

class MovieRepository(
    val remote: MovieDataSource.Remote,
    val local: MovieDataSource.Local
) : MovieDataSource.Remote, MovieDataSource.Local {

    override fun getMovieFavoriteById(id: Int): Single<Movie> {
        return local.getMovieFavoriteById(id)
    }

    override fun getAllMovie(): Flowable<List<Movie>> {
        return local.getAllMovie()
    }

    override fun insertMovie(movie: Movie) {
        local.insertMovie(movie)
    }

    override fun deleteMovie(movie: Movie) {
        local.deleteMovie(movie)
    }

    override fun getMovieById(id: Int): Single<Movie> {
        return remote.getMovieById(id)
    }

    override fun getMovies(page: Int): Single<List<Movie>> {
        return remote.getMovies(page)
    }
}