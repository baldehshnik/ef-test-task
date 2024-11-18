package com.sparkfusion.data.entity

import com.google.gson.annotations.SerializedName

data class CoursesListDataEntity(

    @SerializedName("courses")
    val courses: List<CourseEntity>
)
