package com.sama.socialteq.di


import com.sama.socialteq.data.repository.Repository
import com.sama.socialteq.data.repository.RepositoryImp
import com.sama.socialteq.data.repository.remote.CloudDataSource
import com.sama.socialteq.data.repository.remote.CloudDataSourceIml
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val testRepositoryModule = module {

    single<Repository>(qualifier  = qualifier(TestInstanceNames.TEST_SERVICE.name)) { RepositoryImp(get()) }

    /**
     * Data sources
     */
    factory<CloudDataSource>(qualifier = qualifier(TestInstanceNames.TEST_SERVICE.name)) {
        CloudDataSourceIml(
            get(qualifier = qualifier(InstanceNames.SERVICE.name))
        )
    }
}
