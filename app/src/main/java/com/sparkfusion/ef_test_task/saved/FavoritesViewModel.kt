package com.sparkfusion.ef_test_task.saved

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.core.dispatchers.IODispatcher
import com.sparkfusion.core.dispatchers.MainDispatcher
import com.sparkfusion.data.entity.LocalCourseDataEntity
import com.sparkfusion.data.repository.ICourseDataRepository
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
    private val courseDataRepository: ICourseDataRepository
) : ViewModel() {

    private val _courses = MutableLiveData<List<LocalCourseDataEntity>>()
    val courses: LiveData<List<LocalCourseDataEntity>> = _courses

    fun readCourses() {
        viewModelScope.launch(ioDispatcher) {
            val f = courseDataRepository.readSavedCourses()
            withContext(mainDispatcher) {
                _courses.value = f.firstOrNull()
            }
        }
    }
}





















