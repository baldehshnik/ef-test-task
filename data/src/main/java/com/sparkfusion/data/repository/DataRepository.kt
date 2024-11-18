package com.sparkfusion.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sparkfusion.core.dispatchers.IODispatcher
import com.sparkfusion.data.entity.CourseEntity
import com.sparkfusion.data.source.ApiService
import com.sparkfusion.data.source.CoursePagingSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val apiService: ApiService
) : IDataRepository {

    override suspend fun readCourses(): Flow<PagingData<CourseEntity>> = withContext(ioDispatcher) {
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CoursePagingSource(apiService, ioDispatcher) }
        ).flow
    }

}














