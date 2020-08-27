package com.sama.socialteq.data.model.remote.error_handler

import android.content.Context
import androidx.annotation.StringRes

data class ErrorObject constructor( var message: String? = null, @StringRes  var resId: Int? = null) {
    fun get(context: Context): String {
        return message ?: if (resId != null) context.getString(resId!!) else "No message"
    }
    fun get(): String {
        return message ?: "No message"
    }

}
