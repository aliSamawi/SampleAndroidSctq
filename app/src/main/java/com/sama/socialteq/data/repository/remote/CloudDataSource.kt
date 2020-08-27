package com.sama.socialteq.data.repository.remote

import com.sama.socialteq.data.model.remote.CallLiveData
import com.sama.socialteq.data.model.remote.response.Home

interface CloudDataSource {

    fun getHomeData(): CallLiveData<Home>

}