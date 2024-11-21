package com.sparkfusion.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sparkfusion.core.common.Answer
import com.sparkfusion.core.dispatchers.IODispatcher
import com.sparkfusion.core.exception.UnexpectedException
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

    override suspend fun readCourses(isPopular: Boolean): Flow<PagingData<CourseEntity>> = withContext(ioDispatcher) {
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CoursePagingSource(courseService, ioDispatcher, isPopular) }
        ).flow
    }

    override suspend fun readCourseById(id: Int): Answer<CourseInfoListDataEntity> =
        safeApiCall(ioDispatcher) {
            ApiResponseHandler(courseService.getCourseById(id)).handleFetchedData()
        }

    override suspend fun insertCourse(localCourseDataEntity: LocalCourseDataEntity): Answer<Unit> =
        withContext(ioDispatcher) {
            try {
                coursesDao.insertCourse(localCourseDataEntity)
                Answer.Success(Unit)
            } catch (_: Exception) {
                Answer.Failure(UnexpectedException())
            }
        }

    override suspend fun deleteCourse(id: Int): Answer<Unit> {
        return withContext(ioDispatcher) {
            return@withContext try {
                coursesDao.deleteCourse(id)
                Answer.Success(Unit)
            } catch (_: Exception) {
                Answer.Failure(UnexpectedException())
            }
        }
    }

    override suspend fun readSavedCourses(): Flow<List<LocalCourseDataEntity>> =
        withContext(ioDispatcher) {
            coursesDao.readCourses()
        }

    override suspend fun existsCourse(id: Int): Answer<Boolean> = withContext(ioDispatcher) {
        try {
            Log.i("TAGTAG", "exists - " + coursesDao.existsCourse(id))
            return@withContext Answer.Success(coursesDao.existsCourse(id) == 1)
        } catch (_: Exception) {
            return@withContext Answer.Failure(UnexpectedException())
        }
    }
}














