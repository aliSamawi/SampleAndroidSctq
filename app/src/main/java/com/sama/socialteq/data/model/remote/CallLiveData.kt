package com.sama.socialteq.data.model.remote

import androidx.lifecycle.MutableLiveData
import retrofit2.Call

class CallLiveData<T>(private val call: Call<T>): MutableLiveData<Resource<T>>() {

    fun cancel() {
        call.cancel()
    }
}
