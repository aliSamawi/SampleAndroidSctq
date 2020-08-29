package com.sama.socialteq.di

import com.sama.socialteq.presentation.main.MainViewModel
import com.sama.socialteq.presentation.main.home.HomeViewModel
import org.koin.dsl.module

val presentationModule = module {
    single{ MainViewModel() }
    single{ HomeViewModel(get()) }
}