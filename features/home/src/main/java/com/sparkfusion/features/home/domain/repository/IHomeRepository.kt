package com.sparkfusion.features.home.domain.repository

import androidx.paging.PagingData
import com.sparkfusion.core.common.Answer
import com.sparkfusion.features.home.domain.model.CourseModel
import kotlinx.coroutines.flow.Flow

interface IHomeRepository {

    suspend fun readCourses(isPopular: Boolean): Flow<PagingData<CourseModel>>

    suspend fun existsCourse(id: Int): Answer<Boolean>

    suspend fun insertCourse(courseModel: CourseModel): Answer<Unit>

    suspend fun deleteCourse(id: Int): Answer<Unit>
}
