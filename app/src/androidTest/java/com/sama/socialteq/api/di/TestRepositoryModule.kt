package com.sama.socialteq.api.di


import com.sama.socialteq.data.repository.Repository
import com.sama.socialteq.data.repository.RepositoryImp
import com.sama.socialteq.data.repository.remote.CloudDataSource
import com.sama.socialteq.data.repository.remote.CloudDataSourceIml
import org.koin.dsl.module

val testRepositoryModule = module {

    single<Repository> { RepositoryImp(get()) }

    /**
     * Data sources
     */
    factory<CloudDataSource>() {
        CloudDataSourceIml(
            get()
        )
    }
}
