package com.sparkfusion.features.favorite.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.core.dispatchers.IODispatcher
import com.sparkfusion.core.dispatchers.MainDispatcher
import com.sparkfusion.features.favorite.domain.model.LocalCourseModel
import com.sparkfusion.features.favorite.domain.repository.IFavoriteRepository
import com.sparkfusion.features.favorite.presentation.router.IFavoriteRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: MainCoroutineDispatcher,
    private val repository: IFavoriteRepository,
    private val favoriteRouter: IFavoriteRouter
) : ViewModel() {

    private val _courses = MutableLiveData<List<LocalCourseModel>>()
    val courses: LiveData<List<LocalCourseModel>> = _courses

    private val _deletionError = MutableLiveData(false)
    val deletionError: LiveData<Boolean> get() = _deletionError

    fun readCourses() {
        viewModelScope.launch(ioDispatcher) {
            val f = repository.readSavedCourses()
            withContext(mainDispatcher) {
                _courses.value = f.firstOrNull()
            }
        }
    }

    fun deleteCourse(id: Int) {
        viewModelScope.launch(ioDispatcher) {
            repository.deleteCourse(id)
                .onSuccess {
                    val currentCourses = _courses.value ?: emptyList()
                    val updatedCourses = currentCourses.filter { it.id != id }

                    withContext(mainDispatcher) {
                        _courses.value = updatedCourses
                    }
                }
                .onFailure {
                    withContext(mainDispatcher) {
                        _deletionError.value = true
                    }
                }
        }
    }

    fun clearDeletionStatus() {
        _deletionError.value = false
    }

    fun navigateToCourseDetails(id: Int) {
        favoriteRouter.navigateToCourseDetails(id)
    }
}





















