package com.example.data.repository

import com.example.domain.repositories.ILoginRepository
import com.example.domain.utils.ValidationResponse
import java.util.regex.Pattern
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepositoryImpl @Inject constructor(): ILoginRepository {

    private val EMAIL_REGEX = Pattern.compile(
        "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    )

    override fun validate(email: String, password: String): ValidationResponse {
        val isEmailValid = validateEmail(email)
        val isPasswordValid = validatePassword(password)

        return ValidationResponse(isEmailValid = isEmailValid, isPasswordValid = isPasswordValid)
    }

    private fun validateEmail(email: String): Boolean {
        return email.isNotEmpty() && EMAIL_REGEX.matcher(email).matches()
    }

    private fun validatePassword(password: String): Boolean {
        return password.isNotEmpty() && password.length >= 6
    }

}