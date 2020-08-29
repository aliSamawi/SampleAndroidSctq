package com.sama.socialteq.presentation.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.sama.socialteq.data.model.remote.error_handler.ErrorObject
import com.sama.socialteq.data.model.remote.response.Home
import com.sama.socialteq.domain.GetHomeUseCase
import com.sama.socialteq.presentation.base.BaseViewModel

class HomeViewModel(private val getHomeUseCase: GetHomeUseCase) : BaseViewModel() {
    private val homeDataSuccessLiveData = MediatorLiveData<Home>()
    private val homeDataErrorLiveData = MediatorLiveData<ErrorObject>()
    private val homeDataLoadingLiveData = MediatorLiveData<Boolean>()

    fun getHomeSuccess() = homeDataSuccessLiveData as LiveData<Home>
    fun getHomeError() = homeDataErrorLiveData as LiveData<ErrorObject>
    fun getHomeDataLoading() = homeDataLoadingLiveData as LiveData<Boolean>

    fun getHomeData(){
        callApi(
            getHomeUseCase(),
            homeDataSuccessLiveData,
            homeDataErrorLiveData,
            homeDataLoadingLiveData
        )
    }
}