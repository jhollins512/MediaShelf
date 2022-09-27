package com.jamaalhollins.movieshelf.core.di

import com.jamaalhollins.movieshelf.core.data.repository.MediaRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MediaRepository(get()) }
}