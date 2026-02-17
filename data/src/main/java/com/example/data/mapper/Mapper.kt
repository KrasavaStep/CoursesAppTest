package com.example.data.mapper

import com.example.data.network.models.CourseModelDTO
import com.example.data.network.models.CoursesList
import com.example.domain.models.CourseModel
import java.text.SimpleDateFormat
import java.util.Locale

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
        startDate = formatDate(courseModelDTO.startDate),
        hasLike = courseModelDTO.hasLike,
        publishDate = formatDate(courseModelDTO.publishDate)
    )

    fun formatDate(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale("ru"))

        val date = inputFormat.parse(inputDate)
        return outputFormat.format(date)
    }

}