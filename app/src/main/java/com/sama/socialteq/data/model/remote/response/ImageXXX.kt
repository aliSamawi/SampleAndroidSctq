package com.sama.socialteq.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class ImageXXX(
    val originalUrl: String,
    @SerializedName("originalUrl@2x") val originalUrl_2x: String,
    @SerializedName("originalUrl@3x") val originalUrl_3x: String,
    @SerializedName("originalUrl@4x") val originalUrl_4x: String,
    val originalUrlPDF: String,
    val originalUrlSVG: String
)