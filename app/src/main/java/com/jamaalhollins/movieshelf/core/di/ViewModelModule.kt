package com.jamaalhollins.movieshelf.core.di

import com.jamaalhollins.movieshelf.feature.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel() }
}