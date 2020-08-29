package com.sama.socialteq.domain

import com.sama.socialteq.data.model.remote.CallLiveData
import com.sama.socialteq.data.model.remote.response.ServiceDetails
import com.sama.socialteq.data.repository.Repository


class GetServiceDetailsUseCase (private val repository: Repository) {

    operator fun invoke(serviceName:String): CallLiveData<ServiceDetails> =
        repository.getServiceDetails(serviceName)

}
