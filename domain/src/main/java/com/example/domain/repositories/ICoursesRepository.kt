package com.example.domain.repositories

import com.example.domain.models.CourseModel
import com.example.domain.utils.NetworkResponse

interface ICoursesRepository {

    suspend fun fetchCourses(): NetworkResponse<List<CourseModel>>

    suspend fun saveCourse(course: CourseModel)

    suspend fun getLikedCourses(): List<CourseModel>

    suspend fun getCourses(): List<CourseModel>

    suspend fun updateCourse(course: CourseModel): Boolean

}