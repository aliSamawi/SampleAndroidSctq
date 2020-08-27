package com.sama.socialteq.data.repository.remote

import com.sama.foursquare.data.model.remote.CallLiveData
import com.sama.socialteq.data.model.remote.response.Home


class CloudDataSourceIml (private val apis: Apis) : CloudDataSource{

    override fun getHomeData(): CallLiveData<Home> = apis.getHomeData()

}