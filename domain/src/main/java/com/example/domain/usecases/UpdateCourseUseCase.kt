package com.example.domain.usecases

import com.example.domain.models.CourseModel
import com.example.domain.repositories.ICoursesRepository

class UpdateCourseUseCase(private val courseRepository: ICoursesRepository) {
    suspend fun execute(course: CourseModel): Boolean {
        return courseRepository.updateCourse(course)
    }
}