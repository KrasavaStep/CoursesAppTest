package com.example.coursesapp.presentation.ui.home

import com.example.domain.models.CourseModel

data class HomeState(
    val isLoading: Boolean,
    val data: List<CourseModel>,
    val errorBody: String,
    val errorCode: Int,
    val exception: String
)