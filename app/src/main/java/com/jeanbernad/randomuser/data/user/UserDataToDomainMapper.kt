package com.jeanbernad.randomuser.data.user

import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.domain.ErrorType
import com.jeanbernad.randomuser.domain.user.UserDomain
import retrofit2.HttpException
import java.net.UnknownHostException

interface UserDataToDomainMapper<T> : Abstract.Mapper {
    fun map(
        id: String,
        fullName: String,
        fullAddress: String,
        gender: String,
        phone: String,
        mail: String,
        country: String,
        city: String,
        coordinates: String,
        birthday: String
    ): T

    fun map(exception: Exception): T
}

class BaseUserDataToDomainMapper : UserDataToDomainMapper<UserDomain> {
    override fun map(
        id: String,
        fullName: String,
        fullAddress: String,
        gender: String,
        phone: String,
        mail: String,
        country: String,
        city: String,
        coordinates: String,
        birthday: String
    ) = UserDomain.Success(
        id,
        fullName,
        fullAddress,
        gender,
        phone,
        mail,
        country,
        city,
        coordinates,
        birthday
    )

    override fun map(exception: Exception) = UserDomain.Fail(
        when (exception) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            else -> ErrorType.GENERIC
        }
    )
}