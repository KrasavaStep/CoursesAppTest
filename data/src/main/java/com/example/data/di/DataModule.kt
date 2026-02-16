package com.example.data.di

import com.example.data.network.CoursesAPI
import com.example.data.repository.CoursesRepositoryImpl
import com.example.domain.repositories.ICoursesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataModule {

    @Singleton
    @Provides
    fun provideCoursesRepository(
        coursesAPI: CoursesAPI
    ): ICoursesRepository {
        return CoursesRepositoryImpl(coursesAPI)
    }

}