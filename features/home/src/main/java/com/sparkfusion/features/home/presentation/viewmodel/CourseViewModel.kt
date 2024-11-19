package com.sparkfusion.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.sparkfusion.features.home.domain.model.CourseModel
import com.sparkfusion.features.home.domain.repository.IHomeRepository
import com.sparkfusion.features.home.presentation.router.IHomeRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val repository: IHomeRepository,
    private val homeRouter: IHomeRouter
) : ViewModel() {

    suspend fun courses(): Flow<PagingData<CourseModel>> = repository.readCourses()

    fun navigateToDetails(id: Int) {
        homeRouter.navigateToDetails(id)
    }
}

