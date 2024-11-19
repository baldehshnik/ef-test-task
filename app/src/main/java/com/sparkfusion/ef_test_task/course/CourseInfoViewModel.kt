package com.sparkfusion.ef_test_task.course

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.core.dispatchers.IODispatcher
import com.sparkfusion.core.dispatchers.MainDispatcher
import com.sparkfusion.data.entity.AuthorDataEntity
import com.sparkfusion.data.entity.CourseInfoDataEntity
import com.sparkfusion.data.entity.LocalCourseDataEntity
import com.sparkfusion.data.repository.ICourseDataRepository
import com.sparkfusion.data.repository.IUserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CourseInfoViewModel @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: MainCoroutineDispatcher,
    private val courseDataRepository: ICourseDataRepository,
    private val userDataRepository: IUserDataRepository
) : ViewModel() {

    private val _course = MutableLiveData<CourseInfoDataEntity>()
    val course: LiveData<CourseInfoDataEntity> get() = _course

    private val _authors = MutableLiveData<List<AuthorDataEntity>>()
    val authors: LiveData<List<AuthorDataEntity>> get() = _authors

    fun saveCourse() {
        viewModelScope.launch(ioDispatcher) {
            val c = course.value ?: return@launch
            courseDataRepository.insertCourse(
                LocalCourseDataEntity(
                    c.id,
                    c.summary,
                    c.cover,
                    c.description,
                    c.created,
                    c.price
                )
            )
        }
    }

    fun deleteCourse() {
        viewModelScope.launch(ioDispatcher) {
            val c = course.value ?: return@launch
            courseDataRepository.deleteCourse(c.id)
        }
    }

    fun readCourse(id: Int) {
        viewModelScope.launch(ioDispatcher) {
            courseDataRepository.readCourseById(id)
                .onSuccess { model ->
                    withContext(mainDispatcher) {
                        _course.value = model.courses[0]
                    }
                    readAuthors(model.courses[0].authors)
                }
                .onFailure {
                    Log.i("TAGTAG", "view model error - " + it.message.toString())
                }
        }
    }

    private suspend fun readAuthors(ids: List<Int>) = withContext(ioDispatcher) {
        val users = mutableListOf<AuthorDataEntity>()
        for (id in ids) {
            userDataRepository.readUserById(id)
                .onSuccess {
                    users.add(it.authors[0])
                }
                .onFailure {
                    Log.i("TAGTAG", "view model error - " + it.message.toString())
                }
        }

        withContext(mainDispatcher) {
            _authors.value = users
        }
    }


}















