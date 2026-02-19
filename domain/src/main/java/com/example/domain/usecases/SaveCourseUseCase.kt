package com.example.domain.usecases

import com.example.domain.models.CourseModel
import com.example.domain.repositories.ICoursesRepository

class SaveCourseUseCase(private val courseRepository: ICoursesRepository) {

    suspend fun execute(course: CourseModel) {
        return courseRepository.saveCourse(course)
    }

}