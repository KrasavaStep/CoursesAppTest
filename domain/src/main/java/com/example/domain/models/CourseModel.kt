package com.example.domain.models

data class CourseModel(
    val id: Int,
    val title: String,
    val text: String,
    val price: Double,
    val rate: Double,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)