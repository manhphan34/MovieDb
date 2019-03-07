package com.example.moviedb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviedb.data.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDAO(): MovieDAO
}