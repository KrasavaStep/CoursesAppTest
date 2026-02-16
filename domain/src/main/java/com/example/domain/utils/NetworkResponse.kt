package com.example.domain.utils

sealed class NetworkResponse<T>(
    val data: T? = null,
    val exceptionMessage: String? = null,
    val responseCode: Int? = null,
    val errorBody: String? = null
) {
    class Success<T>(data: T) : NetworkResponse<T>(data = data)
    class Error<T>(errorBody: String, code: Int) :
        NetworkResponse<T>(errorBody = errorBody, responseCode = code)

    class Exception<T>(exceptionMessage: String) :
        NetworkResponse<T>(exceptionMessage = exceptionMessage)
}