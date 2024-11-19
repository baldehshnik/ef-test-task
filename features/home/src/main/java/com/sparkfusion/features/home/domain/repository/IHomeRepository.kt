package com.sparkfusion.features.home.domain.repository

import androidx.paging.PagingData
import com.sparkfusion.features.home.domain.model.CourseModel
import kotlinx.coroutines.flow.Flow

interface IHomeRepository {

    suspend fun readCourses(): Flow<PagingData<CourseModel>>
}
