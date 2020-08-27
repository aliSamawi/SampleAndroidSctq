package com.sama.socialteq.di

import com.sama.socialteq.domain.GetHomeUseCase
import org.koin.dsl.module

val domainModule = module {
    single{ GetHomeUseCase(get()) }
}