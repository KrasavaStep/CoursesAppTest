package com.example.coursesapp.presentation.ui.bookmarks

import com.example.domain.models.CourseModel

data class BookmarksState(
    val isLoading: Boolean,
    val data: List<CourseModel>
)
