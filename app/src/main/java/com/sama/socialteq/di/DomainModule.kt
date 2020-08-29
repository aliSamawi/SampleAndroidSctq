package com.sama.socialteq.di

import com.sama.socialteq.domain.GetHomeUseCase
import com.sama.socialteq.domain.GetServiceDetailsUseCase
import org.koin.dsl.module

val domainModule = module {
    single{ GetHomeUseCase(get()) }
    single{ GetServiceDetailsUseCase(get()) }
}