package com.sparkfusion.features.details.domain.model

data class CourseInfoModel(
    val id: Int,
    val summary: String,
    val cover: String,
    val description: String,
    val created: String,
    val price: String,
    val authors: List<Int>,
    val canonicalUrl: String,
    val isSaved: Boolean = false
)
