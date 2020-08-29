package com.sama.socialteq.data.model.remote.response

data class CoverImage(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val fileName: String,
    val id: String,
    val originalUrl: String,
    val path: String,
    val serviceId: String,
    val thumbnails: Thumbnails,
    val type: String,
    val updatedAt: String
)