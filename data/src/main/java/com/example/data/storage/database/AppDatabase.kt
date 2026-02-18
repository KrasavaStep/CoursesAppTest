package com.example.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.storage.database.entities.CourseEntity

@Database(entities = [CourseEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCoursesDAO(): CoursesDAO
}