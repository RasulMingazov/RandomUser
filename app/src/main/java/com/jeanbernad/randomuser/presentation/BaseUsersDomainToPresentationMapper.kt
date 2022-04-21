package com.jeanbernad.randomuser.presentation

import com.jeanbernad.randomuser.core.ErrorPresentationMapper
import com.jeanbernad.randomuser.domain.ErrorType
import com.jeanbernad.randomuser.domain.UserDomain
import com.jeanbernad.randomuser.domain.UserDomainToPresentationMapper
import com.jeanbernad.randomuser.domain.UsersDomainToPresentationMapper

class BaseUsersDomainToPresentationMapper(
    private val errorMapper: ErrorPresentationMapper,
    private val userMapper: UserDomainToPresentationMapper<UserPresentationModel>
) : UsersDomainToPresentationMapper<UsersPresentationModel> {
    override fun map(usersDomain: List<UserDomain>) =
        UsersPresentationModel.Base(usersDomain.map { it.map(userMapper) })

    override fun map(errorType: ErrorType) =
        UsersPresentationModel.Base(listOf(UserPresentationModel.Fail(errorMapper.map(errorType))))
}
