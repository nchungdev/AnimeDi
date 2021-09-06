package com.chun.data.remote

import android.content.Context
import retrofit2.Response

sealed class ApiException(override val message: String) : Exception(message) {

    class UnauthenticatedException(message: String) : ApiException(message)

    class ClientException(message: String) : ApiException(message)

    class BadServerException(message: String) : ApiException(message)

    companion object {
        fun <T> categorizeException(context: Context, response: Response<T>): Throwable {
            return when (response.code()) {
                401 -> UnauthenticatedException("")
                in 400..499 -> ClientException("Client Error")
                in 500..599 -> BadServerException("Server error")
                else -> RuntimeException("Unexpected response $response")
            }
        }

        fun categorizeException(context: Context, throwable: Throwable): Throwable {
            return throwable
        }
    }
}
