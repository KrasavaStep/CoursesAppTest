package com.example.coursesapp.di

import com.example.domain.repositories.ICoursesRepository
import com.example.domain.usecases.GetCoursesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetCoursesUseCase(
        repository: ICoursesRepository
    ): GetCoursesUseCase {
        return GetCoursesUseCase(repository)
    }

}