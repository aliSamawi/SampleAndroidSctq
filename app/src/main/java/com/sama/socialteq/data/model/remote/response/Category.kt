package com.sama.socialteq.data.model.remote.response

data class Category(
    val _id: String,
    val description: String,
    val descriptions: Descriptions,
    val displayType: String,
    val hasNewBadge: Boolean,
    val id: String,
    val image: Image,
    val isActive: Boolean,
    val shortDescription: String,
    val shortDescriptions: ShortDescriptions,
    val slogan: String,
    val slogans: Slogans,
    val slug: String,
    val sort: Int,
    val subTitle: String,
    val subTitles: SubTitles,
    val title: String,
    val titles: Titles
)