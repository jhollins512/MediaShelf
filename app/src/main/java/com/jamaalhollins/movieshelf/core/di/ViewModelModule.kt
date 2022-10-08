package com.jamaalhollins.movieshelf.core.di

import com.jamaalhollins.movieshelf.feature.home.presentation.HomeViewModel
import com.jamaalhollins.movieshelf.feature.mediaDetails.presentation.MovieDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get(), get(), get(), get(), get()) }
    viewModel { MovieDetailsViewModel(get()) }
}