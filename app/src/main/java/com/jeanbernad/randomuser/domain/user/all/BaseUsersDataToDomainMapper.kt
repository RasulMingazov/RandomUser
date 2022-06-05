package com.jeanbernad.randomuser.domain.user.all

import com.jeanbernad.randomuser.data.user.UserData
import com.jeanbernad.randomuser.data.user.UserDataToDomainMapper
import com.jeanbernad.randomuser.data.user.all.UsersDataToDomainMapper
import com.jeanbernad.randomuser.domain.common.ErrorDomainMapper
import com.jeanbernad.randomuser.domain.user.UserDomain
import java.lang.Exception

class BaseUsersDataToDomainMapper(
    private val errorMapper: ErrorDomainMapper,
    private val userMapper: UserDataToDomainMapper<UserDomain>
) : UsersDataToDomainMapper<UsersDomain> {

    override fun map(users: List<UserData>): UsersDomain {
        val domainUsers = users.map { it.map(userMapper) }
        return UsersDomain.Success(domainUsers)
    }

    override fun map(exception: Exception) = UsersDomain.Fail(
        errorMapper.map(exception)
    )

    override fun map() = UsersDomain.Empty
}