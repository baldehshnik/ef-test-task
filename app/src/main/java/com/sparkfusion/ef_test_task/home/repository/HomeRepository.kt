package com.sparkfusion.ef_test_task.home.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.sparkfusion.core.common.Answer
import com.sparkfusion.core.dispatchers.IODispatcher
import com.sparkfusion.data.repository.ICourseDataRepository
import com.sparkfusion.ef_test_task.home.factory.HomeCourseFactory
import com.sparkfusion.ef_test_task.home.factory.LocalCourseDataEntityFactory
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
    private val courseDataRepository: ICourseDataRepository,
    private val localCourseDataEntityFactory: LocalCourseDataEntityFactory
) : IHomeRepository {

    override suspend fun readCourses(): Flow<PagingData<CourseModel>> = withContext(ioDispatcher) {
        courseDataRepository.readCourses().map {
            it.map { entity ->
                var isSaved = false
                existsCourse(entity.id).onSuccess { saved -> isSaved = saved }

                homeCourseFactory.mapTo(entity).copy(isSaved = isSaved)
            }
        }
    }

    override suspend fun existsCourse(id: Int): Answer<Boolean> = withContext(ioDispatcher) {
        courseDataRepository.existsCourse(id)
    }

    override suspend fun insertCourse(courseModel: CourseModel): Answer<Unit> =
        withContext(ioDispatcher) {
            courseDataRepository.insertCourse(localCourseDataEntityFactory.mapFrom(courseModel))
        }

    override suspend fun deleteCourse(id: Int): Answer<Unit> = withContext(ioDispatcher) {
        courseDataRepository.deleteCourse(id)
    }
}













