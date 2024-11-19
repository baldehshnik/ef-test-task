package com.sparkfusion.data.repository

import androidx.paging.PagingData
import com.sparkfusion.core.common.Answer
import com.sparkfusion.data.entity.CourseEntity
import com.sparkfusion.data.entity.CourseInfoListDataEntity
import kotlinx.coroutines.flow.Flow

interface ICourseDataRepository {

    suspend fun readCourses(): Flow<PagingData<CourseEntity>>

    suspend fun readCourseById(id: Int): Answer<CourseInfoListDataEntity>
}

























