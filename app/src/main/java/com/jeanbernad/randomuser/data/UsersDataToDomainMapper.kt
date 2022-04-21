package com.jeanbernad.randomuser.data

import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.domain.ErrorType
import com.jeanbernad.randomuser.domain.UserDomain
import com.jeanbernad.randomuser.domain.UsersDomain
import retrofit2.HttpException
import java.net.UnknownHostException

interface UsersDataToDomainMapper<T> : Abstract.Mapper.DataToDomain<List<UserData>, T>

class BaseUsersDataToDomainMapper(
    private val userMapper: UserDataToDomainMapper<UserDomain>
) : UsersDataToDomainMapper<UsersDomain> {

    override fun map(data: List<UserData>): UsersDomain {
        val domainUsers = data.map { it.map(userMapper) }
        return UsersDomain.Success(domainUsers)
    }

    override fun map(e: Exception) = UsersDomain.Fail(
        when (e) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            else -> ErrorType.GENERIC
        }
    )
}