package com.example.coursesapp.di

import com.example.domain.repositories.ICoursesRepository
import com.example.domain.repositories.ILoginRepository
import com.example.domain.usecases.FetchCoursesUseCase
import com.example.domain.usecases.GetCoursesFromDbUseCase
import com.example.domain.usecases.GetLikedCoursesFromDbUseCase
import com.example.domain.usecases.SaveCourseUseCase
import com.example.domain.usecases.UpdateCourseUseCase
import com.example.domain.usecases.ValidateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideFetchCoursesUseCase(
        repository: ICoursesRepository
    ): FetchCoursesUseCase {
        return FetchCoursesUseCase(repository)
    }

    @Provides
    fun provideValidateUseCase(
        repository: ILoginRepository
    ): ValidateUseCase {
        return ValidateUseCase(repository)
    }

    @Provides
    fun provideSaveCourseUseCase(
        repository: ICoursesRepository
    ): SaveCourseUseCase {
        return SaveCourseUseCase(repository)
    }

    @Provides
    fun provideUpdateCourseUseCase(
        repository: ICoursesRepository
    ): UpdateCourseUseCase {
        return UpdateCourseUseCase(repository)
    }

    @Provides
    fun provideGetCoursesFromDbUseCase(
        repository: ICoursesRepository
    ): GetCoursesFromDbUseCase {
        return GetCoursesFromDbUseCase(repository)
    }

    @Provides
    fun provideGetLikedCoursesFromDbUseCase(
        repository: ICoursesRepository
    ): GetLikedCoursesFromDbUseCase {
        return GetLikedCoursesFromDbUseCase(repository)
    }

}