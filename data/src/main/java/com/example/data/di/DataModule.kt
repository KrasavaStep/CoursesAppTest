package com.example.data.di

import com.example.data.network.CoursesAPI
import com.example.data.repository.CoursesRepositoryImpl
import com.example.domain.repositories.ICoursesRepository
import com.example.domain.usecases.GetCoursesUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindCourseRepository(
        courseRepositoryImpl: CoursesRepositoryImpl
    ): ICoursesRepository

}