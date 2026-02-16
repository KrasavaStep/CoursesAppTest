package com.example.domain.usecases

import com.example.domain.models.CourseModel
import com.example.domain.repositories.ICoursesRepository

class GetCoursesUseCase(private val courseRepository: ICoursesRepository) {

    fun execute(): List<CourseModel> {
        return courseRepository.getCourses()
    }

}