package com.sparkfusion.data.entity

import com.google.gson.annotations.SerializedName

data class CourseInfoDataEntity(

    @SerializedName("id")
    val id: Int,

    @SerializedName("summary")
    val summary: String,

    @SerializedName("cover")
    val cover: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("create_date")
    val created: String,

    @SerializedName("display_price")
    val price: String,

    @SerializedName("authors")
    val authors: List<Int>
)
