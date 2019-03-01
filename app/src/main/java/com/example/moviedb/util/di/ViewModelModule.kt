package com.example.moviedb.util.di

import com.example.moviedb.screen.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.experimental.builder.create

val viewModelModule = module {
    viewModel { create<HomeViewModel>() }
}