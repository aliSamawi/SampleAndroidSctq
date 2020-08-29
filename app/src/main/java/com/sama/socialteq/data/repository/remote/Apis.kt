package com.sama.socialteq.data.repository.remote

import com.sama.socialteq.data.model.remote.CallLiveData
import com.sama.socialteq.data.model.remote.response.Home
import com.sama.socialteq.data.model.remote.response.ServiceDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface Apis {

    @GET("v2/home")
    fun getHomeDataAsync(): CallLiveData<Home>

    @GET("v2/categories/{service}/services")
    fun getServiceDetailsAsync(@Path("service") serviceName : String): CallLiveData<ServiceDetails>

}