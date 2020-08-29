package com.sama.socialteq.data.model.remote.response

data class Data(
    val _id: String,
    val basePrice: Int,
    val categoryId: String,
    val coverImage: CoverImage,
    val coverImageId: String,
    val discountPercentage: Int,
    val hasDiscount: Boolean,
    val id: String,
    val image: ImageXX,
    val isActive: Boolean,
    val isSpecial: Boolean,
    val listBasePrice: Int,
    val shortDescription: String,
    val shortDescriptions: ShortDescriptionsX,
    val slug: String,
    val sort: Int,
    val subTitle: String,
    val subTitles: SubTitlesX,
    val title: String,
    val titles: TitlesX
)