package com.sparkfusion.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sparkfusion.core.common.Answer
import com.sparkfusion.core.dispatchers.IODispatcher
import com.sparkfusion.data.entity.CourseEntity
import com.sparkfusion.data.entity.CourseInfoListDataEntity
import com.sparkfusion.data.entity.LocalCourseDataEntity
import com.sparkfusion.data.source.CoursePagingSource
import com.sparkfusion.data.source.CourseService
import com.sparkfusion.data.source.CoursesDao
import com.sparkfusion.data.utils.ApiResponseHandler
import com.sparkfusion.data.utils.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CourseDataRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val courseService: CourseService,
    private val coursesDao: CoursesDao
) : ICourseDataRepository {

    override suspend fun readCourses(): Flow<PagingData<CourseEntity>> = withContext(ioDispatcher) {
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CoursePagingSource(courseService, ioDispatcher) }
        ).flow
    }

    override suspend fun readCourseById(id: Int): Answer<CourseInfoListDataEntity> =
        safeApiCall(ioDispatcher) {
            ApiResponseHandler(courseService.getCourseById(id)).handleFetchedData()
        }

    override suspend fun insertCourse(localCourseDataEntity: LocalCourseDataEntity) =
        withContext(ioDispatcher) {
            try {
                coursesDao.insertCourse(localCourseDataEntity)
            } catch (_: Exception) {
            }
        }

    override suspend fun deleteCourse(id: Int) = withContext(ioDispatcher) {
        try {
            coursesDao.deleteCourse(id)
        } catch (_: Exception) {
        }
    }

    override suspend fun readSavedCourses(): Flow<List<LocalCourseDataEntity>> = withContext(ioDispatcher) {
        coursesDao.readCourses()
    }

    override suspend fun existsCourse(id: Int): Int = withContext(ioDispatcher) {
        try {
            return@withContext coursesDao.existsCourse(id)
        } catch (_: Exception) {
            return@withContext 0
        }
    }
}














