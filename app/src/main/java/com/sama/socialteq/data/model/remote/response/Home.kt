package com.sama.socialteq.data.model.remote.response

data class Home(
    val categories: List<Category>,
    val promotions: List<Promotion>,
    val subTitle: String,
    val title: String
)