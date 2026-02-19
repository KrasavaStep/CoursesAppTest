package com.example.data.di

import com.example.data.repository.CoursesRepositoryImpl
import com.example.data.repository.LoginRepositoryImpl
import com.example.domain.repositories.ICoursesRepository
import com.example.domain.repositories.ILoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindCourseRepository(
        courseRepositoryImpl: CoursesRepositoryImpl
    ): ICoursesRepository

    @Binds
    abstract fun bindLoginRepository(
        loginRepository: LoginRepositoryImpl
    ): ILoginRepository

}