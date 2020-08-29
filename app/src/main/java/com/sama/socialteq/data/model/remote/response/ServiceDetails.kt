package com.sama.socialteq.data.model.remote.response

data class ServiceDetails(
    val checkout: Checkout,
    val data: List<Data>,
    val description: String,
    val image: ImageXXX,
    val slogan: String,
    val slug: String,
    val title: String
)