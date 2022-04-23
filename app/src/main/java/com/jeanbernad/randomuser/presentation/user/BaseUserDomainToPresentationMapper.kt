package com.jeanbernad.randomuser.presentation.user

import com.jeanbernad.randomuser.core.ErrorPresentationMapper
import com.jeanbernad.randomuser.domain.ErrorType
import com.jeanbernad.randomuser.domain.user.UserDomainToPresentationMapper

class BaseUserDomainToPresentationMapper(
    private val errorMapper: ErrorPresentationMapper
) : UserDomainToPresentationMapper<UserPresentationModel> {

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
    ) = UserPresentationModel.Success(
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

    override fun map(errorType: ErrorType) = UserPresentationModel.Fail(errorMapper.map(errorType))
}