package com.example.data.network.utils

import com.example.domain.utils.NetworkResponse
import retrofit2.Response

suspend fun <T, K> safeApiCall(call: suspend () -> Response<K>, transform: (K) -> T): NetworkResponse<T> {
    return try {
        val response = call.invoke()
        if (response.isSuccessful) {
            NetworkResponse.Success(data = transform(response.body()!!))
        } else {
            val errorBody = response.errorBody()?.toString()!!
            NetworkResponse.Error(errorBody = errorBody, code = response.code(), )
        }
    } catch (exception: Exception) {
        NetworkResponse.Exception(exceptionMessage = exception.toString())
    }
}