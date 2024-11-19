package com.sparkfusion.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sparkfusion.data.entity.LocalCourseDataEntity

@Database(entities = [LocalCourseDataEntity::class], version = 1)
abstract class CourseDatabase : RoomDatabase() {
    abstract val coursesDao: CoursesDao
}