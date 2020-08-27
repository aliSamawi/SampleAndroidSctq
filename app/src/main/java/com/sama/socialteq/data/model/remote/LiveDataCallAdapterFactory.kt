package com.sama.socialteq.data.model.remote

import androidx.lifecycle.LiveData
import com.sama.foursquare.data.model.remote.CallLiveData
import com.sama.socialteq.data.model.remote.error_handler.ErrorManager
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class LiveDataCallAdapterFactory private constructor() : CallAdapter.Factory() {
    companion object {
        @JvmStatic @JvmName("create")
        operator fun invoke() = LiveDataCallAdapterFactory()
    }

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (CallLiveData::class.java != getRawType(returnType) &&
            Resource::class.java != getRawType(returnType)) {
            return null
        }
        check(returnType is ParameterizedType) { "return type must be parameterized as LiveData<Foo> or LiveData<out Foo> or Resource<Foo>" }
        val responseType = getParameterUpperBound(0, returnType)

        val rawDeferredType = getRawType(responseType)
        return if (rawDeferredType == Response::class.java) {
            if (responseType !is ParameterizedType) {
                throw IllegalStateException(
                    "Response must be parameterized as Response<Foo> or Response<out Foo>")
            }
            ResponseCallAdapter<Any>(getParameterUpperBound(0, responseType))
        } else {
            if (CallLiveData::class.java == getRawType(returnType)) {
                BodyCallAdapter<Any>(responseType)
            } else {
                SyncCallAdapter<Any>(responseType)
            }
        }
    }

    private class BodyCallAdapter<T>(
        private val responseType: Type
    ) : CallAdapter<T, LiveData<Resource<T>>> {

        override fun responseType() = responseType

        override fun adapt(call: Call<T>): CallLiveData<T> {
            val liveData = CallLiveData(call)

            if (call.isCanceled) {
                return liveData
            }

            call.enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    if (call.isCanceled) return
                    liveData.postValue(Resource.error(ErrorManager.handleError(t), null))
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (call.isCanceled) return

                    if (response.isSuccessful) {
                        liveData.postValue(Resource.success(response.body()))
                    } else {
                        liveData.postValue(
                            Resource.error(
                                ErrorManager.handleError(
                                    HttpException(
                                        response
                                    )
                                ), null
                            )
                        )
                    }
                }
            })

            return liveData
        }
    }

    private class ResponseCallAdapter<T>(
        private val responseType: Type
    ) : CallAdapter<T, LiveData<Resource<T>>> {

        override fun responseType() = responseType

        override fun adapt(call: Call<T>): CallLiveData<T> {
            val liveData = CallLiveData(call)

            if (call.isCanceled) {
                return liveData
            }

            call.enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    if (call.isCanceled) return
                    liveData.postValue(Resource.error(ErrorManager.handleError(t), null))
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (call.isCanceled) return
                    liveData.postValue(Resource.success(response.body()))
                }
            })

            return liveData
        }
    }


    private class SyncCallAdapter<T>(
        private val responseType: Type
    ) : CallAdapter<T, Resource<T>> {

        override fun responseType() = responseType

        override fun adapt(call: Call<T>): Resource<T> {
            val result = call.execute()
            if (result.isSuccessful) {
                return Resource.success(result.body())
            } else {
                return Resource.error(
                    ErrorManager.handleError(Throwable(result.errorBody()?.string())),
                    null
                )
            }
        }
    }
}
