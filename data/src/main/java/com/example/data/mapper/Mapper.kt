package com.example.data.mapper

import com.example.data.network.models.CourseModelDTO
import com.example.data.network.models.CoursesList
import com.example.data.storage.database.entities.CourseEntity
import com.example.domain.models.CourseModel
import java.text.SimpleDateFormat
import java.util.Locale

object Mapper {

    fun convertToCourseModelList(coursesList: CoursesList) = coursesList.courses.map {
        convertToCoursesModelFromDTO(it)
    }

    fun convertToCoursesModelFromDTO(courseModelDTO: CourseModelDTO) = CourseModel(
        id = courseModelDTO.id,
        title = courseModelDTO.title,
        text = courseModelDTO.text,
        price = courseModelDTO.price + "₽",
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

    fun convertToCourseModelFromDB(entity: CourseEntity) = CourseModel(
        id = entity.id,
        title = entity.title,
        text = entity.text,
        price = entity.price,
        rate = entity.rate,
        startDate = entity.startDate,
        hasLike = entity.hasLike,
        publishDate = entity.publishDate
    )

    fun convertToDbModelFromCourseModel(courseModel: CourseModel) = CourseEntity(
        id = courseModel.id,
        title = courseModel.title,
        text = courseModel.text,
        price = courseModel.price,
        rate = courseModel.rate,
        startDate = courseModel.startDate,
        hasLike = courseModel.hasLike,
        publishDate = courseModel.publishDate
    )

}