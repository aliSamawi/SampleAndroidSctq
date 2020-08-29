package com.sama.socialteq.data.repository.remote

import com.sama.socialteq.data.model.remote.CallLiveData
import com.sama.socialteq.data.model.remote.response.Home
import com.sama.socialteq.data.model.remote.response.ServiceDetails


class CloudDataSourceIml (private val apis: Apis) : CloudDataSource{

    override fun getHomeData(): CallLiveData<Home> = apis.getHomeDataAsync()

    override fun getServiceDetails(serviceName:String): CallLiveData<ServiceDetails> =
        apis.getServiceDetailsAsync(serviceName)

}