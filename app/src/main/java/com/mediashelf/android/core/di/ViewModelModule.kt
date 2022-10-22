package com.mediashelf.android.core.di

import com.mediashelf.android.feature.home.presentation.HomeViewModel
import com.mediashelf.android.feature.movieDetails.presentation.MovieDetailsAboutViewModel
import com.mediashelf.android.feature.movieDetails.presentation.MovieDetailsViewModel
import com.mediashelf.android.feature.search.SearchViewModel
import com.mediashelf.android.feature.tvShowDetails.presentation.TVShowDetailsAboutViewModel
import com.mediashelf.android.feature.tvShowDetails.presentation.TvShowDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get(), get(), get(), get(), get()) }
    viewModel { MovieDetailsViewModel(get(), get(), get(), get()) }
    viewModel { TvShowDetailsViewModel(get(), get(), get(), get()) }
    viewModel { MovieDetailsAboutViewModel(get(), get()) }
    viewModel { TVShowDetailsAboutViewModel(get(), get()) }
    viewModel { SearchViewModel(get()) }
}