package com.sparkfusion.features.favorite.domain.repository

import com.sparkfusion.core.common.Answer
import com.sparkfusion.features.favorite.domain.model.LocalCourseModel
import kotlinx.coroutines.flow.Flow

interface IFavoriteRepository {

    suspend fun readSavedCourses(): Flow<List<LocalCourseModel>>

    suspend fun deleteCourse(id: Int): Answer<Unit>
}