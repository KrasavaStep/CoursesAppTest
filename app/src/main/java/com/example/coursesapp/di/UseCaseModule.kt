package com.example.coursesapp.di

import com.example.domain.repositories.ICoursesRepository
import com.example.domain.repositories.ILoginRepository
import com.example.domain.usecases.GetCoursesUseCase
import com.example.domain.usecases.ValidateUseCase
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

    @Provides
    fun provideValidateUseCase(
        repository: ILoginRepository
    ): ValidateUseCase {
        return ValidateUseCase(repository)
    }

}