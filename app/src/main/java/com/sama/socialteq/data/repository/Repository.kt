package com.sama.socialteq.data.repository

import com.sama.socialteq.data.model.remote.CallLiveData
import com.sama.socialteq.data.model.remote.response.Home
import com.sama.socialteq.data.model.remote.response.ServiceDetails

interface Repository {

    fun getHomeData(): CallLiveData<Home>

    fun getServiceDetails(serviceName:String): CallLiveData<ServiceDetails>
}