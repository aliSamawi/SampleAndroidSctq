package com.sama.socialteq.di

import com.sama.socialteq.presentation.home.HomeViewModel
import org.koin.dsl.module

val presentationModule = module {
    single{ HomeViewModel(get()) }
}