package com.jeanbernad.randomuser.domain

import com.jeanbernad.randomuser.core.ErrorType
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

interface ErrorDomainMapper {
    fun map(exception: Exception): ErrorType

    class Base : ErrorDomainMapper {
        override fun map(exception: Exception) =
            when (exception) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC
            }
    }
}
