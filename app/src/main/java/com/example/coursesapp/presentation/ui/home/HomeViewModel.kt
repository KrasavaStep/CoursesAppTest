package com.example.coursesapp.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.CourseModel
import com.example.domain.usecases.GetCoursesUseCase
import com.example.domain.utils.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCoursesUseCase: GetCoursesUseCase
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

            is HomeEvent.SaveCoursesEvent -> {

            }
        }
    }

    private fun getCoursesFromNetwork() {
        viewModelScope.launch {
            _state.value = _state.value?.copy(
                isLoading = true,
            )
            when (val response = getCoursesUseCase.execute()) {
                is NetworkResponse.Error -> {
                    _state.value = _state.value?.copy(
                        isLoading = false,
                        errorCode = response.responseCode!!,
                        errorBody = response.errorBody.toString()
                    )
                }

                is NetworkResponse.Exception -> {
                    _state.value = _state.value?.copy(
                        isLoading = false,
                        exception = response.exceptionMessage.toString()
                    )
                }

                is NetworkResponse.Success -> {
                    _state.value = _state.value?.copy(
                        isLoading = false,
                        data = response.data ?: emptyList()
                    )
                }
            }
        }
    }

}