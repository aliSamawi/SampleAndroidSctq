package com.sama.socialteq.presentation.base

import androidx.lifecycle.*
import com.sama.socialteq.data.model.remote.CallLiveData
import com.sama.socialteq.data.model.remote.Resource
import com.sama.socialteq.data.model.remote.error_handler.ErrorManager
import com.sama.socialteq.data.model.remote.error_handler.ErrorObject

open class BaseViewModel : ViewModel() {

    fun <T> callApi(
        source: CallLiveData<T>,
        success: MediatorLiveData<T>? = null,
        error: MediatorLiveData<ErrorObject>,
        loading: MutableLiveData<Boolean>? = null
    ) {
        loading?.postValue(true)
        success?.addSource(source) {
            if (it.status == Resource.Status.SUCCESS) {
                loading?.postValue(false)
                success.postValue(it.data)
            }
        }

        error.addSource(source) { exception ->
            if (exception.status == Resource.Status.ERROR) {
                val errorModel = ErrorManager.handleError(exception.error?.e)
                loading?.postValue(false)
                error.postValue(ErrorObject(errorModel.errorMessage))
//                    checkSpecialErrors(errorModel)
            }
        }
    }

}
