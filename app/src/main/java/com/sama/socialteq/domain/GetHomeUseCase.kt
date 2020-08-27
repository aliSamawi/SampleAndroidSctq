package com.sama.socialteq.domain

import com.sama.socialteq.data.model.remote.CallLiveData
import com.sama.socialteq.data.model.remote.response.Home
import com.sama.socialteq.data.repository.Repository


class GetHomeUseCase (private val repository: Repository) {

    operator fun invoke(): CallLiveData<Home> =
        repository.getHomeData()

}
