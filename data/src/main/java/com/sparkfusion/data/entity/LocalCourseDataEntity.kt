package com.sparkfusion.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("courses")
data class LocalCourseDataEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int,

    @ColumnInfo("summary")
    val summary: String,

    @ColumnInfo("cover")
    val cover: String,

    @ColumnInfo("description")
    val description: String,

    @ColumnInfo("created")
    val created: String,

    @ColumnInfo("price")
    val price: String
)
