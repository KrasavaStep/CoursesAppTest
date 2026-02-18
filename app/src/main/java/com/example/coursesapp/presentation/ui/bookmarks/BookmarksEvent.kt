package com.example.coursesapp.presentation.ui.bookmarks

import com.example.domain.models.CourseModel

sealed class BookmarksEvent {

    class GetLikedCoursesEvent(): BookmarksEvent()

    class RemoveFromBookmark(val course: CourseModel): BookmarksEvent()

}