package com.example.moviedb.util.di

import android.content.Context
import androidx.room.Room
import com.example.moviedb.data.MovieDataSource
import com.example.moviedb.data.local.MovieDatabase
import com.example.moviedb.data.local.MovieLocalDataSource
import com.example.moviedb.data.remote.MovieRemoteDataSource
import com.example.moviedb.data.repository.MovieRepository
import org.koin.dsl.module.module
import org.koin.experimental.builder.create

val appModule = module {
    single { createDatabaseName() }
    single { createAppDatabase(get(), get()) }
    single { createMovieDao(get()) }
    single<MovieDataSource.Remote> { create<MovieRemoteDataSource>() }
    single<MovieDataSource.Local> { create<MovieLocalDataSource>() }
    single { create<MovieRepository>() }
}
val modules = listOf(appModule, networkModule, viewModelModule)

fun createDatabaseName() = "moviedbdatabase"

fun createAppDatabase(dbName: String, context: Context) =
    Room.databaseBuilder(context, MovieDatabase::class.java, dbName).build()

fun createMovieDao(appDatabase: MovieDatabase) = appDatabase.movieDAO()