package com.mediashelf.android.core.di

import com.mediashelf.android.core.data.repository.TMDBMediaRepository
import com.mediashelf.android.core.domain.repository.MediaRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { TMDBMediaRepository(get()) }
    single<MediaRepository> { TMDBMediaRepository(get()) }
}