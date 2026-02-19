package com.example.coursesapp.presentation.ui.home

import com.example.domain.models.CourseModel

sealed class HomeEvent {
    class GetCoursesEvent(): HomeEvent()
    class AddToBookmark(val course: CourseModel): HomeEvent()

    class SortCourseList(): HomeEvent()
}