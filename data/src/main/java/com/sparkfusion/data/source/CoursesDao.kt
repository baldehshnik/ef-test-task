package com.sparkfusion.data.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sparkfusion.data.entity.LocalCourseDataEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CoursesDao {

    @Insert
    suspend fun insertCourse(localCourseDataEntity: LocalCourseDataEntity)

    @Query("DELETE FROM courses WHERE id = :id")
    suspend fun deleteCourse(id: Int)

    @Query("SELECT * FROM courses")
    fun readCourses(): Flow<List<LocalCourseDataEntity>>

    @Query("SELECT COUNT(*) FROM courses WHERE id = :id")
    suspend fun existsCourse(id: Int): Int
}











