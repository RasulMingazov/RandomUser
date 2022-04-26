package com.jeanbernad.randomuser.domain.user

import com.jeanbernad.randomuser.core.ErrorDomainMapper
import com.jeanbernad.randomuser.core.ErrorType
import com.jeanbernad.randomuser.data.user.UserDataToDomainMapper
import com.jeanbernad.randomuser.presentation.user.UserPresentationModel
import retrofit2.HttpException
import java.net.UnknownHostException

class BaseUserDataToDomainMapper(
    private val errorMapper: ErrorDomainMapper
) : UserDataToDomainMapper<UserDomain> {
    override fun map(
        fullName: String,
        fullAddress: String,
        gender: String,
        phone: String,
        mail: String,
        country: String,
        city: String,
        coordinates: String,
        birthday: String,
        image: String
    ) = UserDomain.Success(
        fullName,
        fullAddress,
        gender,
        phone,
        mail,
        country,
        city,
        coordinates,
        birthday,
        image
    )

    override fun map(exception: Exception) = UserDomain.Fail(errorMapper.map(exception))
}