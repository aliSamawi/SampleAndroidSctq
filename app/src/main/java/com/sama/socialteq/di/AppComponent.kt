package com.sama.socialteq.di

import com.sama.socialteq.BuildConfig

val appComponent = listOf(
    presentationModule,
    repositoryModule,
    domainModule,
    networkModule(BuildConfig.BASE_URL)
)
