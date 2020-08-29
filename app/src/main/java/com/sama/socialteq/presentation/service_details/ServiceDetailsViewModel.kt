package com.sama.socialteq.presentation.service_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.sama.socialteq.data.model.remote.error_handler.ErrorObject
import com.sama.socialteq.data.model.remote.response.ServiceDetails
import com.sama.socialteq.domain.GetServiceDetailsUseCase
import com.sama.socialteq.presentation.base.BaseViewModel

class ServiceDetailsViewModel(private val getServiceDetailsUseCase: GetServiceDetailsUseCase) : BaseViewModel(){

    private val serviceDetailsSuccessLiveData = MediatorLiveData<ServiceDetails>()
    private val serviceDetailsErrorLiveData = MediatorLiveData<ErrorObject>()
    private val serviceDetailsLoadingLiveData = MediatorLiveData<Boolean>()

    fun getServiceDetailsSuccess() = serviceDetailsSuccessLiveData as LiveData<ServiceDetails>
    fun getServiceDetailsError() = serviceDetailsErrorLiveData as LiveData<ErrorObject>
    fun getServiceDetailsDataLoading() = serviceDetailsLoadingLiveData as LiveData<Boolean>

    fun loadServiceDetails(serviceName:String){
        callApi(
            getServiceDetailsUseCase(serviceName),
            serviceDetailsSuccessLiveData,
            serviceDetailsErrorLiveData,
            serviceDetailsLoadingLiveData
        )
    }
}