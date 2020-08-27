package com.sama.socialteq.data.repository.remote

import com.sama.socialteq.data.model.remote.CallLiveData
import com.sama.socialteq.data.model.remote.response.Home
import retrofit2.http.GET

interface Apis {

    @GET("v2/home")
    fun getHomeData(): CallLiveData<Home>

}