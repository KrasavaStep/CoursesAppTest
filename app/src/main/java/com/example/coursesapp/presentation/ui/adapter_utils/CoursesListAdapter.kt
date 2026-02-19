package com.example.coursesapp.presentation.ui.adapter_utils

import com.example.domain.models.CourseModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter


class CoursesListAdapter(
    onItemClick: (ListItem.CourseItem) -> Unit,
    onBookmarkClick: (ListItem.CourseItem) -> Unit
) : AsyncListDifferDelegationAdapter<ListItem>(diffUtilCallback) {


    init {
        delegatesManager.addDelegate(
            courseDelegate(
                onItemClick = onItemClick,
                onBookmarksClick = onBookmarkClick
            )
        )
    }

    fun submitCoursesList(courses: List<CourseModel>) {

        items = courses.map {
            ListItem.CourseItem(
                id = it.id,
                title = it.title,
                text = it.text,
                price = it.price,
                rate = it.rate,
                startDate = it.startDate,
                hasLike = it.hasLike,
                publishDate = it.publishDate
            )
        }
    }
}