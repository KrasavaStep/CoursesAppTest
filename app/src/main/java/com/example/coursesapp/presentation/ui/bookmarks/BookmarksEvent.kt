package com.example.coursesapp.presentation.ui.bookmarks

sealed class BookmarksEvent {

    class GetLikedCoursesEvent(): BookmarksEvent()

}