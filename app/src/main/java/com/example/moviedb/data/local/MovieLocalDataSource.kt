package com.example.moviedb.data.local

import com.example.moviedb.data.MovieDataSource.Local
import com.example.moviedb.data.model.Movie
import io.reactivex.Flowable
import io.reactivex.Single

class MovieLocalDataSource(val movieDAO: MovieDAO) : Local {
    override fun getMovieFavoriteById(id: Int): Single<Movie> {
        return movieDAO.getMovieById(id)
    }

    override fun getAllMovie(): Flowable<List<Movie>> {
        return movieDAO.getAllMovie()
    }

    override fun insertMovie(movie: Movie) {
        movieDAO.insertMovie(movie)
    }

    override fun deleteMovie(movie: Movie) {
        movieDAO.deleteMovie(movie)
    }
}