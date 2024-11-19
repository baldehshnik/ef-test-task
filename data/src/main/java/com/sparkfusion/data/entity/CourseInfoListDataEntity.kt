package com.sparkfusion.data.entity

import com.google.gson.annotations.SerializedName

data class CourseInfoListDataEntity(

    @SerializedName("courses")
    val courses: List<CourseInfoDataEntity>
)
