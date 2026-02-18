package com.example.data.storage.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import androidx.room.Update
import com.example.data.storage.database.entities.CourseEntity

@Dao
interface CoursesDAO {

    @Query("SELECT * FROM course_table WHERE hasLike = 1")
    suspend fun getLikedCoursesList(): List<CourseEntity>

    @Query("SELECT * FROM course_table")
    suspend fun getCoursesList(): List<CourseEntity>

    @Insert(onConflict = IGNORE)
    suspend fun saveCourse(course: CourseEntity)

    @Update
    suspend fun updateCourse(course: CourseEntity): Int

}