package com.sparkfusion.ef_test_task.saved.repository

import com.sparkfusion.core.common.Answer
import com.sparkfusion.core.dispatchers.IODispatcher
import com.sparkfusion.data.repository.ICourseDataRepository
import com.sparkfusion.ef_test_task.saved.factory.LocalCourseDataEntityFactory
import com.sparkfusion.features.favorite.domain.model.LocalCourseModel
import com.sparkfusion.features.favorite.domain.repository.IFavoriteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val courseDataRepository: ICourseDataRepository,
    private val localCourseDataEntityFactory: LocalCourseDataEntityFactory
) : IFavoriteRepository {

    override suspend fun readSavedCourses(): Flow<List<LocalCourseModel>> = withContext(ioDispatcher) {
        courseDataRepository.readSavedCourses().map {
            it.map { dataEntity -> localCourseDataEntityFactory.mapTo(dataEntity) }
        }
    }

    override suspend fun deleteCourse(id: Int): Answer<Unit> = withContext(ioDispatcher) {
        courseDataRepository.deleteCourse(id)
    }
}

















