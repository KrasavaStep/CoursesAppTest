package com.example.coursesapp.presentation.ui.bookmarks

import android.util.Log
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        }
    }

    private fun getLikedCourses() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.postValue(_state.value?.copy(isLoading = true))

            val data = getLikedCoursesFromDbUseCase.execute()
            Log.d("DB_DATA", data.toString())

            _state.postValue(
                _state.value?.copy(
                    isLoading = false,
                    data = data
                )
            )
        }
    }

}