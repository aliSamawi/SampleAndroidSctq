package com.sama.socialteq.data.repository

import com.sama.foursquare.data.model.remote.CallLiveData
import com.sama.socialteq.data.model.remote.response.Home
import com.sama.socialteq.data.repository.remote.CloudDataSource

class RepositoryImp(private val cloudDataSource: CloudDataSource) : Repository {

    override fun getHomeData(): CallLiveData<Home> = cloudDataSource.getHomeData()

}