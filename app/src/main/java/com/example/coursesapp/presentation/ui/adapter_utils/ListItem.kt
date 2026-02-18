package com.example.coursesapp.presentation.ui.adapter_utils

import com.example.domain.models.CourseModel

sealed class ListItem {

    data class CourseItem(
        val id: Int,
        val title: String,
        val text: String,
        val price: String,
        val rate: String,
        val startDate: String,
        val hasLike: Boolean,
        val publishDate: String
    ): ListItem()

}

fun ListItem.CourseItem.convertToCourseModel() = CourseModel(
    id = this.id,
    title = this.title,
    text = this.text,
    price = this.price,
    rate = this.rate,
    startDate = this.startDate,
    hasLike = this.hasLike,
    publishDate = this.publishDate
)