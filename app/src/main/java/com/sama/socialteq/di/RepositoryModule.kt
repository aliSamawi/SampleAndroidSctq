package com.sama.socialteq.di


import com.sama.socialteq.BuildConfig
import com.sama.socialteq.data.model.remote.LiveDataCallAdapterFactory
import com.sama.socialteq.data.model.remote.LiveDataResponseBodyConverterFactory
import com.sama.socialteq.data.repository.Repository
import com.sama.socialteq.data.repository.RepositoryImp
import com.sama.socialteq.data.repository.remote.Apis
import com.sama.socialteq.data.repository.remote.CloudDataSource
import com.sama.socialteq.data.repository.remote.CloudDataSourceIml
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val repositoryModule = module {

    /**
     * Repository
     */
    single<Repository> { RepositoryImp(get()) }

    /**
     * OkHttp instances
     */
    val httpLoggingInterceptor =
        HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

    single {
        OkHttpClient
            .Builder()
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(httpLoggingInterceptor)
                }
                connectTimeout(45, TimeUnit.SECONDS)
                readTimeout(30, TimeUnit.SECONDS)
                writeTimeout(30, TimeUnit.SECONDS)
            }
            .build()
    }


    /**
     * Retrofit instances
     */
    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(LiveDataResponseBodyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    /**
     * Services instances
     */
    single {
        get<Retrofit>().create(Apis::class.java)
    }

    /**
     * Data sources
     */
    single<CloudDataSource> {
        CloudDataSourceIml(
            get()
        )
    }
}
