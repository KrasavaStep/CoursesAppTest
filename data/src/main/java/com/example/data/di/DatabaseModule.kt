package com.example.data.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.storage.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(
        application: Application
    ): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "courses_db")
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .build()
    }

    @Provides
    fun provideDAO(db: AppDatabase) = db.getCoursesDAO()

}