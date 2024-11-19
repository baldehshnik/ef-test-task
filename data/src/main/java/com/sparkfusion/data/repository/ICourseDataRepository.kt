package com.sparkfusion.data.repository

import androidx.paging.PagingData
import com.sparkfusion.core.common.Answer
import com.sparkfusion.data.entity.CourseEntity
import com.sparkfusion.data.entity.CourseInfoListDataEntity
import com.sparkfusion.data.entity.LocalCourseDataEntity
import kotlinx.coroutines.flow.Flow

interface ICourseDataRepository {

    suspend fun readCourses(): Flow<PagingData<CourseEntity>>

    suspend fun readCourseById(id: Int): Answer<CourseInfoListDataEntity>

    suspend fun insertCourse(localCourseDataEntity: LocalCourseDataEntity)

    suspend fun deleteCourse(id: Int)

    suspend fun readSavedCourses(): Flow<List<LocalCourseDataEntity>>

    suspend fun existsCourse(id: Int): Int
}

























