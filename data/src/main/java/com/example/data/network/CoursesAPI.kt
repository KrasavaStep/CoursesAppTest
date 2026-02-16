package com.example.data.network

import com.example.data.network.models.CoursesList
import retrofit2.Response
import retrofit2.http.GET

interface CoursesAPI {

    @GET("/u/0/uc")
    suspend fun fetchCourses(): Response<CoursesList>

}