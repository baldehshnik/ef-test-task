package com.sparkfusion.features.details.domain.repository

import com.sparkfusion.core.common.Answer
import com.sparkfusion.features.details.domain.model.AuthorModel
import com.sparkfusion.features.details.domain.model.CourseInfoModel
import com.sparkfusion.features.details.domain.model.LocalCourseModel

interface IDetailsRepository {

    suspend fun insertCourse(localCourseModel: LocalCourseModel): Answer<Unit>

    suspend fun deleteCourse(id: Int): Answer<Unit>

    suspend fun readCourseById(id: Int): Answer<CourseInfoModel>

    suspend fun readUserById(id: Int): Answer<AuthorModel>

    suspend fun existsCourse(id: Int): Answer<Boolean>
}