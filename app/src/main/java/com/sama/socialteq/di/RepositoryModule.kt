package com.sama.socialteq.di


import com.sama.socialteq.data.repository.Repository
import com.sama.socialteq.data.repository.RepositoryImp
import com.sama.socialteq.data.repository.remote.CloudDataSource
import com.sama.socialteq.data.repository.remote.CloudDataSourceIml
import org.koin.dsl.module

val repositoryModule = module {

    /**
     * Repository
     */
    single<Repository> { RepositoryImp(get()) }

    /**
     * Data sources
     */
    factory<CloudDataSource> {
        CloudDataSourceIml(
            get()
        )
    }
}
