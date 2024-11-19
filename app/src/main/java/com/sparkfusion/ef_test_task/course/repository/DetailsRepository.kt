package com.sparkfusion.ef_test_task.course.repository

import com.sparkfusion.core.common.Answer
import com.sparkfusion.core.dispatchers.IODispatcher
import com.sparkfusion.data.repository.ICourseDataRepository
import com.sparkfusion.data.repository.IUserDataRepository
import com.sparkfusion.ef_test_task.course.factory.AuthorDataEntityFactory
import com.sparkfusion.ef_test_task.course.factory.CourseInfoDataEntityFactory
import com.sparkfusion.ef_test_task.course.factory.LocalCourseDataEntityFactory
import com.sparkfusion.features.details.domain.model.AuthorModel
import com.sparkfusion.features.details.domain.model.CourseInfoModel
import com.sparkfusion.features.details.domain.model.LocalCourseModel
import com.sparkfusion.features.details.domain.repository.IDetailsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailsRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val courseDataRepository: ICourseDataRepository,
    private val usersDataRepository: IUserDataRepository,
    private val authorDataEntityFactory: AuthorDataEntityFactory,
    private val courseInfoDataEntityFactory: CourseInfoDataEntityFactory,
    private val localCourseDataEntityFactory: LocalCourseDataEntityFactory
) : IDetailsRepository {

    override suspend fun insertCourse(localCourseModel: LocalCourseModel) = withContext(ioDispatcher) {
        courseDataRepository.insertCourse(localCourseDataEntityFactory.mapFrom(localCourseModel))
    }

    override suspend fun deleteCourse(id: Int) = withContext(ioDispatcher) {
        courseDataRepository.deleteCourse(id)
    }

    override suspend fun readCourseById(id: Int): Answer<CourseInfoModel> = withContext(ioDispatcher) {
        courseDataRepository.readCourseById(id).suspendMap {
            courseInfoDataEntityFactory.mapTo(it.courses[0])
        }
    }

    override suspend fun readUserById(id: Int): Answer<AuthorModel> = withContext(ioDispatcher) {
        usersDataRepository.readUserById(id).suspendMap {
            authorDataEntityFactory.mapTo(it.authors[0])
        }
    }
}

















