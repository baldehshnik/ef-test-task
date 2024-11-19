package com.sparkfusion.features.details.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.core.dispatchers.IODispatcher
import com.sparkfusion.core.dispatchers.MainDispatcher
import com.sparkfusion.features.details.domain.model.AuthorModel
import com.sparkfusion.features.details.domain.model.CourseInfoModel
import com.sparkfusion.features.details.domain.model.LocalCourseModel
import com.sparkfusion.features.details.domain.repository.IDetailsRepository
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
    private val repository: IDetailsRepository
) : ViewModel() {

    private val _course = MutableLiveData<CourseInfoModel>()
    val course: LiveData<CourseInfoModel> get() = _course

    private val _authors = MutableLiveData<List<AuthorModel>>()
    val authors: LiveData<List<AuthorModel>> get() = _authors

    fun saveCourse() {
        viewModelScope.launch(ioDispatcher) {
            val c = course.value ?: return@launch
            repository.insertCourse(
                LocalCourseModel(
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
            repository.deleteCourse(c.id)
        }
    }

    fun readCourse(id: Int) {
        viewModelScope.launch(ioDispatcher) {
            repository.readCourseById(id)
                .onSuccess { model ->
                    withContext(mainDispatcher) {
                        _course.value = model
                    }
                    readAuthors(model.authors)
                }
                .onFailure {
                    Log.i("TAGTAG", "view model error - " + it.message.toString())
                }
        }
    }

    private suspend fun readAuthors(ids: List<Int>) = withContext(ioDispatcher) {
        val users = mutableListOf<AuthorModel>()
        for (id in ids) {
            repository.readUserById(id)
                .onSuccess {
                    users.add(it)
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















