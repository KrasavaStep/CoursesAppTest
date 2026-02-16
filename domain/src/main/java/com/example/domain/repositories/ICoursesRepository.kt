package com.example.domain.repositories

import com.example.domain.models.CourseModel

interface ICoursesRepository {

    fun getCourses(): List<CourseModel>

    fun saveCourse(course: CourseModel): Boolean

}