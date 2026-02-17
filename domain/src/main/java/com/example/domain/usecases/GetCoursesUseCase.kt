package com.example.domain.usecases

import com.example.domain.models.CourseModel
import com.example.domain.repositories.ICoursesRepository
import com.example.domain.utils.NetworkResponse
import javax.inject.Inject

class GetCoursesUseCase(
    private val courseRepository: ICoursesRepository
) {
    suspend fun execute(): NetworkResponse<List<CourseModel>> {
        return courseRepository.fetchCourses()
    }

}