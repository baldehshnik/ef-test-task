package com.sparkfusion.data.repository

import androidx.paging.PagingData
import com.sparkfusion.data.entity.CourseEntity
import kotlinx.coroutines.flow.Flow

interface IDataRepository {

    suspend fun readCourses(): Flow<PagingData<CourseEntity>>
}

























