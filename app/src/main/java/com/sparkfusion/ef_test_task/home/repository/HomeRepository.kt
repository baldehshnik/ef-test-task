package com.sparkfusion.ef_test_task.home.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.sparkfusion.core.dispatchers.IODispatcher
import com.sparkfusion.data.repository.ICourseDataRepository
import com.sparkfusion.ef_test_task.home.factory.HomeCourseFactory
import com.sparkfusion.features.home.domain.model.CourseModel
import com.sparkfusion.features.home.domain.repository.IHomeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val homeCourseFactory: HomeCourseFactory,
    private val courseDataRepository: ICourseDataRepository
) : IHomeRepository {

    override suspend fun readCourses(): Flow<PagingData<CourseModel>> = withContext(ioDispatcher) {
        courseDataRepository.readCourses().map {
            it.map { entity ->
                homeCourseFactory.mapTo(entity)
            }
        }
    }
}













