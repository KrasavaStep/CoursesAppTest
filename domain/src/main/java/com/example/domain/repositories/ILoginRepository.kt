package com.example.domain.repositories

import com.example.domain.utils.ValidationResponse

interface ILoginRepository {

    fun validate(email: String, password: String): ValidationResponse

}