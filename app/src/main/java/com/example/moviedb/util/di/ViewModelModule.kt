package com.example.moviedb.util.di

import com.example.moviedb.screen.detail.DetailViewModel
import com.example.moviedb.screen.favorite.FavoriteFragment
import com.example.moviedb.screen.favorite.FavoriteViewModel
import com.example.moviedb.screen.home.HomeViewModel
import com.example.moviedb.screen.main.MainViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.experimental.builder.create

val viewModelModule = module {
    viewModel { create<HomeViewModel>() }
    viewModel { create<MainViewModel>() }
    viewModel { create<DetailViewModel>() }
    viewModel { create<FavoriteViewModel>() }
}