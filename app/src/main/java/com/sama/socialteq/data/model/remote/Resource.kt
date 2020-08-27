package com.sama.socialteq.data.model.remote

import com.sama.socialteq.data.model.remote.error_handler.ErrorModel

data class Resource<out T>(val status: Status, val data: T?, val error: ErrorModel?) {
    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(error: ErrorModel?, data: T?): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                error
            )
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }

        fun <T> empty(data: T?): Resource<T> {
            return Resource(
                Status.EMPTY,
                data,
                null
            )
        }
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING,
        EMPTY
    }
}
