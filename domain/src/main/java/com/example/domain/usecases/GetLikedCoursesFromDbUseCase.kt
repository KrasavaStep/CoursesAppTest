package com.example.domain.usecases

import com.example.domain.models.CourseModel
import com.example.domain.repositories.ICoursesRepository

class GetLikedCoursesFromDbUseCase(private val courseRepository: ICoursesRepository) {

    suspend fun execute(): List<CourseModel> {
        return courseRepository.getLikedCourses()
    }

}