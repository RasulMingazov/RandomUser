package com.jeanbernad.randomuser.presentation.user.all

import com.jeanbernad.randomuser.core.ErrorType
import com.jeanbernad.randomuser.domain.user.UserDomain
import com.jeanbernad.randomuser.domain.user.UserDomainToPresentationMapper
import com.jeanbernad.randomuser.domain.user.all.UsersDomainToPresentationMapper
import com.jeanbernad.randomuser.presentation.common.ErrorPresentationMapper
import com.jeanbernad.randomuser.presentation.user.UserPresentationModel

class BaseUsersDomainToPresentationMapper(
    private val errorMapper: ErrorPresentationMapper,
    private val userMapper: UserDomainToPresentationMapper<UserPresentationModel>
) : UsersDomainToPresentationMapper<UsersPresentationModel> {

    override fun map(users: List<UserDomain>) =
        UsersPresentationModel.Base(users.map { it.map(userMapper) })

    override fun map(errorType: ErrorType) =
        UsersPresentationModel.Base(listOf(UserPresentationModel.Fail(errorMapper.map(errorType))))

    override fun map() = UsersPresentationModel.Empty
}