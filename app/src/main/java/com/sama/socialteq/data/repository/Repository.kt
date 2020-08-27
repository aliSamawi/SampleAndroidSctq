package com.sama.socialteq.data.repository

import com.sama.foursquare.data.model.remote.CallLiveData
import com.sama.socialteq.data.model.remote.response.Home

interface Repository {

    fun getHomeData(): CallLiveData<Home>
}