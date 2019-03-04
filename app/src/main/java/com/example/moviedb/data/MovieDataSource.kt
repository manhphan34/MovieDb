package com.example.moviedb.data

import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.model.MovieResponse
import io.reactivex.Flowable
import io.reactivex.Single

interface MovieDataSource {
    interface Remote {
        fun getMovieById(id: Int): Single<Movie>

        fun getMovies(page: Int): Single<List<Movie>>
    }

    interface Local {
        fun getAllMovie(): Flowable<List<Movie>>

        fun insertMovie(movie: Movie)

        fun deleteMovie(movie: Movie)

        fun getMovieFavoriteById(id: Int): Single<Movie>
    }
}