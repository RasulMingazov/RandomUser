package com.jeanbernad.randomuser.presentation.user

import com.jeanbernad.randomuser.presentation.common.ErrorPresentationMapper
import com.jeanbernad.randomuser.core.ErrorType
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
        birthday: String,
        image: String
    ) = UserPresentationModel.Success(
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

    override fun map(errorType: ErrorType) = UserPresentationModel.Fail(errorMapper.map(errorType))
}