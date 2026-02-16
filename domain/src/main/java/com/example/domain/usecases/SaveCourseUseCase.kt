package com.example.domain.usecases

import com.example.domain.models.CourseModel
import com.example.domain.repositories.ICoursesRepository

class SaveCourseUseCase(private val courseRepository: ICoursesRepository) {

    fun execute(course: CourseModel): Boolean {
        return courseRepository.saveCourse(course)
    }

}