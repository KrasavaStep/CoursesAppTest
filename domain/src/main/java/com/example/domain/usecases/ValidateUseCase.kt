package com.example.domain.usecases

import com.example.domain.repositories.ICoursesRepository
import com.example.domain.repositories.ILoginRepository
import com.example.domain.utils.ValidationResponse
import kotlin.math.log

class ValidateUseCase(
    private val loginRepository: ILoginRepository
) {

    fun execute(email: String, password: String): ValidationResponse {
        return loginRepository.validate(email, password)
    }

}