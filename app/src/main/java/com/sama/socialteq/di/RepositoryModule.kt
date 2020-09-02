package com.sama.socialteq.di


import com.sama.socialteq.data.repository.Repository
import com.sama.socialteq.data.repository.RepositoryImp
import com.sama.socialteq.data.repository.remote.CloudDataSource
import com.sama.socialteq.data.repository.remote.CloudDataSourceIml
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val repositoryModule = module {

    /**
     * Repository
     */
    single<Repository>(qualifier  = qualifier(InstanceNames.SERVICE.name)) { RepositoryImp(get()) }

    /**
     * Data sources
     */
    factory<CloudDataSource>(qualifier = qualifier(InstanceNames.SERVICE.name)) {
        CloudDataSourceIml(
            get(qualifier = qualifier(InstanceNames.SERVICE.name))
        )
    }
}
