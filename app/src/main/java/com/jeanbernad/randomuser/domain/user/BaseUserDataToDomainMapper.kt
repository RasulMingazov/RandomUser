package com.jeanbernad.randomuser.domain.user

import com.jeanbernad.randomuser.domain.common.ErrorDomainMapper
import com.jeanbernad.randomuser.data.user.UserDataToDomainMapper
import javax.inject.Inject

class BaseUserDataToDomainMapper @Inject constructor(
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
        image: String,
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