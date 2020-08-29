package com.sama.socialteq.di

import com.sama.socialteq.presentation.main.MainViewModel
import com.sama.socialteq.presentation.main.home.HomeViewModel
import com.sama.socialteq.presentation.service_details.ServiceDetailsViewModel
import org.koin.dsl.module

val presentationModule = module {
    factory{ MainViewModel() }
    factory{ HomeViewModel(get()) }
    factory{ ServiceDetailsViewModel(get()) }
}