package com.example.data.mapper

import com.example.data.network.models.CourseModelDTO
import com.example.data.network.models.CoursesList
import com.example.domain.models.CourseModel

object Mapper {

    fun convertToCourseModelList(coursesList: CoursesList) = coursesList.courses.map {
        convertToCoursesModel(it)
    }

    fun convertToCoursesModel(courseModelDTO: CourseModelDTO) = CourseModel(
        id = courseModelDTO.id,
        title = courseModelDTO.title,
        text = courseModelDTO.text,
        price = courseModelDTO.price,
        rate = courseModelDTO.rate,
        startDate = courseModelDTO.startDate,
        hasLike = courseModelDTO.hasLike,
        publishDate = courseModelDTO.publishDate
    )

}