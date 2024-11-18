package com.sparkfusion.data.entity

import com.google.gson.annotations.SerializedName

data class CourseEntity(

    val id: Int,
    val summary: String,
    val cover: String,
    val description: String,

    @SerializedName("create_date")
    val created: String,

    @SerializedName("display_price")
    val price: String
)
