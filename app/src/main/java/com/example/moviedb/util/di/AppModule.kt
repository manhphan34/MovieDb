package com.example.moviedb.util.di

import com.example.moviedb.data.MovieDataSource
import com.example.moviedb.data.remote.MovieRemoteDataSource
import com.example.moviedb.data.repository.MovieRepository
import org.koin.dsl.module.module
import org.koin.experimental.builder.create

val appModule = module {
    single<MovieDataSource.Remote> { create<MovieRemoteDataSource>() }
    single { create<MovieRepository>() }
}
val modules = listOf(appModule, networkModule, viewModelModule)