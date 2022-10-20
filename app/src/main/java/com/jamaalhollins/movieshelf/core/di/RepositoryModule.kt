package com.jamaalhollins.movieshelf.core.di

import com.jamaalhollins.movieshelf.core.data.repository.TMDBMediaRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { TMDBMediaRepository(get()) }
}