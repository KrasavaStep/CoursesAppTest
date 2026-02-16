package com.example.data.repository

import com.example.data.mapper.Mapper
import com.example.data.network.CoursesAPI
import com.example.data.network.utils.safeApiCall
import com.example.domain.models.CourseModel
import com.example.domain.repositories.ICoursesRepository
import com.example.domain.utils.NetworkResponse

class CoursesRepositoryImpl(
    private val coursesAPI: CoursesAPI
): ICoursesRepository {

    override suspend fun fetchCourses(): NetworkResponse<List<CourseModel>> =
        safeApiCall(
            call = {coursesAPI.fetchCourses()},
            transform = { apiResponse -> Mapper.convertToCourseModelList(apiResponse) }
        )


    override suspend fun saveCourse(course: CourseModel): Boolean {
        TODO("Not yet implemented")
    }
}