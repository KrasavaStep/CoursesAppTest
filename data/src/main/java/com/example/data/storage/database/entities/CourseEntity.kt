package com.example.data.storage.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "course_table")
data class CourseEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean = false,
    val publishDate: String
)
