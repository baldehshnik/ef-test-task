package com.sparkfusion.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.sparkfusion.core.dispatchers.IODispatcher
import com.sparkfusion.features.home.domain.model.CourseModel
import com.sparkfusion.features.home.domain.repository.IHomeRepository
import com.sparkfusion.features.home.presentation.router.IHomeRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val repository: IHomeRepository,
    private val homeRouter: IHomeRouter
) : ViewModel() {

    private val _coursesStateFlow = MutableStateFlow<PagingData<CourseModel>>(PagingData.empty())
    val coursesStateFlow: StateFlow<PagingData<CourseModel>> = _coursesStateFlow.asStateFlow()

    init {
        loadCourses()
    }

    fun navigateToDetails(id: Int) {
        homeRouter.navigateToDetails(id)
    }

    fun changeCourseSaveStatus(courseModel: CourseModel) {
        if (courseModel.isSaved) deleteCourse(courseModel.id)
        else insertCourse(courseModel)
    }

    fun reloadCourses(isPopular: Boolean) {
        _coursesStateFlow.value = PagingData.empty()
        loadCourses(isPopular)
    }

    private fun loadCourses(isPopular: Boolean = false) {
        viewModelScope.launch(ioDispatcher) {
            repository.readCourses(isPopular)
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                    _coursesStateFlow.value = pagingData
                }
        }
    }

    private fun insertCourse(courseModel: CourseModel) {
        viewModelScope.launch(ioDispatcher) {
            repository.insertCourse(courseModel)
                .onSuccess {
                    updateCourseState(courseModel.id, true)
                }
        }
    }

    private fun deleteCourse(id: Int) {
        viewModelScope.launch(ioDispatcher) {
            repository.deleteCourse(id)
                .onSuccess {
                    updateCourseState(id, false)
                }
        }
    }

    private fun updateCourseState(courseId: Int, isSaved: Boolean) {
        _coursesStateFlow.value = _coursesStateFlow.value.map { courseModel ->
            if (courseModel.id == courseId) {
                courseModel.copy(isSaved = isSaved)
            } else {
                courseModel
            }
        }
    }
}






















