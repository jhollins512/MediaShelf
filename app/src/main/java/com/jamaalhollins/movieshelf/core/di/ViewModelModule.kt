package com.jamaalhollins.movieshelf.core.di

import com.jamaalhollins.movieshelf.feature.home.presentation.HomeViewModel
import com.jamaalhollins.movieshelf.feature.movieDetails.presentation.MovieDetailsAboutViewModel
import com.jamaalhollins.movieshelf.feature.movieDetails.presentation.MovieDetailsViewModel
import com.jamaalhollins.movieshelf.feature.tvShowDetails.presentation.TvShowDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get(), get(), get(), get(), get()) }
    viewModel { MovieDetailsViewModel(get(), get(), get(), get()) }
    viewModel { TvShowDetailsViewModel(get(), get(), get(), get()) }
    viewModel { MovieDetailsAboutViewModel(get()) }
}