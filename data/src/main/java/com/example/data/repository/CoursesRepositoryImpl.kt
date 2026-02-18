package com.example.data.repository

import com.example.data.mapper.Mapper
import com.example.data.network.CoursesAPI
import com.example.data.network.utils.safeApiCall
import com.example.data.storage.database.AppDatabase
import com.example.data.storage.database.CoursesDAO
import com.example.domain.models.CourseModel
import com.example.domain.repositories.ICoursesRepository
import com.example.domain.utils.NetworkResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoursesRepositoryImpl @Inject constructor(
    private val coursesAPI: CoursesAPI,
    private val coursesDAO: CoursesDAO
): ICoursesRepository {

    override suspend fun fetchCourses(): NetworkResponse<List<CourseModel>> =
        safeApiCall(
            call = {coursesAPI.fetchCourses()},
            transform = { apiResponse -> Mapper.convertToCourseModelList(apiResponse) }
        )


    override suspend fun saveCourse(course: CourseModel) {
        coursesDAO.saveCourse(Mapper.convertToDbModelFromCourseModel(course))
    }

    override suspend fun getLikedCourses(): List<CourseModel> {
        return coursesDAO.getLikedCoursesList().map { Mapper.convertToCourseModelFromDB(it) }
    }

    override suspend fun getCourses(): List<CourseModel> {
        return coursesDAO.getCoursesList().map { Mapper.convertToCourseModelFromDB(it) }
    }

    override suspend fun updateCourse(course: CourseModel): Boolean {
        val result = coursesDAO.updateCourse(Mapper.convertToDbModelFromCourseModel(course))
        return result > 0
    }
}