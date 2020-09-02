package com.sama.socialteq.di


import com.sama.socialteq.BuildConfig
import com.sama.socialteq.data.model.remote.LiveDataCallAdapterFactory
import com.sama.socialteq.data.model.remote.LiveDataResponseBodyConverterFactory
import com.sama.socialteq.data.repository.remote.Apis
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun networkModule(baseUrl: String) = module {

    /**
     * OkHttp instances
     */
    val httpLoggingInterceptor =
        HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

    factory {
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
    factory {
        Retrofit.Builder()
            .client(get())
            .baseUrl(baseUrl)
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

}