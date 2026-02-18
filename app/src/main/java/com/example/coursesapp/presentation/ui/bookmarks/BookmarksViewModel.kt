package com.example.coursesapp.presentation.ui.bookmarks

import android.util.Log
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.CourseModel
import com.example.domain.usecases.GetLikedCoursesFromDbUseCase
import com.example.domain.usecases.UpdateCourseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val getLikedCoursesFromDbUseCase: GetLikedCoursesFromDbUseCase,
    private val updateCourseUseCase: UpdateCourseUseCase
) : ViewModel() {

    private val _state = MutableLiveData<BookmarksState>()
    val state: LiveData<BookmarksState> = _state

    init {
        _state.value = BookmarksState(
            isLoading = false,
            data = emptyList()
        )
    }

    fun sendEvent(event: BookmarksEvent) {
        when (event) {
            is BookmarksEvent.GetLikedCoursesEvent -> {
                getLikedCourses()
            }

            is BookmarksEvent.RemoveFromBookmark -> {
                updateCourse(event.course)
            }
        }
    }

    private fun getLikedCourses() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.postValue(_state.value?.copy(isLoading = true))

            val data = getLikedCoursesFromDbUseCase.execute()

            _state.postValue(
                _state.value?.copy(
                    isLoading = false,
                    data = data
                )
            )
        }
    }

    private fun updateCourse(course: CourseModel){
        viewModelScope.launch(Dispatchers.IO) {
            val newCourse = course.copy(hasLike = false)
            updateCourseUseCase.execute(newCourse)

            val list = _state.value?.data?.toMutableList()
            list?.forEach {
                if (it.id == course.id) {
                    list.remove(it)
                }
            }
            _state.postValue(_state.value?.copy(data = list?.toList() ?: emptyList()))

        }
    }

}