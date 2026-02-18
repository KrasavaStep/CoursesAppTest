package com.example.coursesapp.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.CourseModel
import com.example.domain.usecases.FetchCoursesUseCase
import com.example.domain.usecases.GetCoursesFromDbUseCase
import com.example.domain.usecases.SaveCourseUseCase
import com.example.domain.usecases.UpdateCourseUseCase
import com.example.domain.utils.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchCoursesUseCase: FetchCoursesUseCase,
    private val saveCourseUseCase: SaveCourseUseCase,
    private val updateCourseUseCase: UpdateCourseUseCase,
    private val getCoursesFromDbUseCase: GetCoursesFromDbUseCase
) : ViewModel() {

    private val _state = MutableLiveData<HomeState>()
    val state: LiveData<HomeState> = _state

    init {
        _state.value = HomeState(
            isLoading = false,
            data = emptyList(),
            errorBody = "",
            errorCode = 200,
            exception = ""
        )
    }

    fun sendEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GetCoursesEvent -> {
                getCoursesFromNetwork()
            }

            is HomeEvent.AddToBookmark -> {
                updateCourse(event.course)
            }

            is HomeEvent.SortCourseList -> {
                sortCoursesList()
            }
        }
    }

    private fun getCoursesFromNetwork() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.postValue(
                _state.value?.copy(
                    isLoading = true,
                )
            )
            when (val response = fetchCoursesUseCase.execute()) {
                is NetworkResponse.Error -> {
                    _state.postValue(
                        _state.value?.copy(
                            isLoading = false,
                            errorCode = response.responseCode!!,
                            errorBody = response.errorBody.toString()
                        )
                    )
                }

                is NetworkResponse.Exception -> {
                    _state.postValue(
                        _state.value?.copy(
                            isLoading = false,
                            exception = response.exceptionMessage.toString()
                        )
                    )
                }

                is NetworkResponse.Success -> {
                    saveCoursesToDb(response.data ?: emptyList())
                    _state.postValue(
                        _state.value?.copy(
                            isLoading = false,
                            data = getCoursesFromDb()
                        )
                    )
                }
            }
        }
    }

    private suspend fun saveCoursesToDb(coursesList: List<CourseModel>) {
        coursesList.forEach {
            saveCourseUseCase.execute(it)
        }
    }

    private suspend fun getCoursesFromDb(): List<CourseModel> {
        return getCoursesFromDbUseCase.execute()
    }

    private fun updateCourse(course: CourseModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val newCourse = if (course.hasLike) {
                course.copy(hasLike = false)
            } else {
                course.copy(hasLike = true)
            }
            val list = _state.value?.data?.toMutableList()
            list?.forEach {
                if (it.id == course.id) {
                    list[list.indexOf(it)] = newCourse
                }
            }
            _state.postValue(_state.value?.copy(data = list?.toList() ?: emptyList()))

            updateCourseUseCase.execute(newCourse)
        }
    }

    private fun sortCoursesList() {
        _state.value = _state.value?.copy(data = _state.value?.data?.sortedBy { it.publishDate }
            ?: emptyList())
    }


}