package com.example.coursesapp.presentation.ui.home

sealed class HomeEvent {
    class GetCoursesEvent(): HomeEvent()
    class SaveCoursesEvent(): HomeEvent()
}