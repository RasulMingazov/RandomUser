package com.jeanbernad.randomuser.domain.user

import com.jeanbernad.randomuser.core.ErrorType
import com.jeanbernad.randomuser.data.user.UserDataToDomainMapper
import retrofit2.HttpException
import java.net.UnknownHostException

class BaseUserDataToDomainMapper : UserDataToDomainMapper<UserDomain> {
    override fun map(
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