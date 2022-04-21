package com.jeanbernad.randomuser.presentation.user

import com.jeanbernad.randomuser.core.ErrorPresentationMapper
import com.jeanbernad.randomuser.domain.ErrorType
import com.jeanbernad.randomuser.domain.user.UserDomainToPresentationMapper


class BaseUserDomainToPresentationMapper(
    private val errorMapper: ErrorPresentationMapper
) : UserDomainToPresentationMapper<UserPresentationModel> {
    override fun map(id: String) = UserPresentationModel.Base(id)

    override fun map(errorType: ErrorType) = UserPresentationModel.Fail(errorMapper.map(errorType))
}