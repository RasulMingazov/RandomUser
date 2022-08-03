package com.jeanbernad.randomuser.presentation.user

import com.jeanbernad.randomuser.presentation.common.ErrorPresentationMapper
import com.jeanbernad.randomuser.core.ErrorType
import com.jeanbernad.randomuser.domain.user.UserDomainToPresentationMapper
import com.jeanbernad.randomuser.presentation.common.DateTimeFormat
import com.jeanbernad.randomuser.presentation.common.TextOperation
import javax.inject.Inject

class BaseUserDomainToPresentationMapper @Inject constructor(
    private val errorMapper: ErrorPresentationMapper,
    private val dateTimeFormat: DateTimeFormat,
    private val textOperation: TextOperation
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
        image: String,
        thumbnail: String,
    ) = UserPresentationModel.Success(
        fullName,
        fullAddress,
        gender,
        phone,
        mail,
        country,
        city,
        coordinates,
        dateTimeFormat.userDateToPresentation(birthday),
        image,
        thumbnail,
        textOperation.combineEveryValue(fullName, birthday, gender, phone, mail, country, city, fullAddress, image)
    )

    override fun map(errorType: ErrorType) = UserPresentationModel.Fail(errorMapper.map(errorType))
}