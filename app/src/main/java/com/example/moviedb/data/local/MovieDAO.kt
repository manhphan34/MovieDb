package com.example.moviedb.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviedb.data.model.Movie
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface MovieDAO {

    @Query("select * from movie")
    fun getAllMovie(): Flowable<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)

    @Query("select * from movie where id = :idMovie")
    fun getMovieById(idMovie: Int): Single<Movie>
}