package com.sparkfusion.ef_test_task.main

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.sparkfusion.data.entity.CourseEntity
import com.sparkfusion.data.repository.ICourseDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val repository: ICourseDataRepository
) : ViewModel() {

    suspend fun courses(): Flow<PagingData<CourseEntity>> = repository.readCourses()
}
