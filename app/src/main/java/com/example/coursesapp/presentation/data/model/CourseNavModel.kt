package com.example.coursesapp.presentation.data.model

import android.os.Parcelable
import kotlinx.serialization.Serializable

@Serializable
data class CourseNavModel(
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
): java.io.Serializable

