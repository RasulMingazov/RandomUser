package com.jeanbernad.randomuser.domain.user.all

import com.jeanbernad.randomuser.core.ErrorType
import com.jeanbernad.randomuser.domain.user.UserDomain

sealed class UsersDomain {
    abstract fun <T> map(mapper: UsersDomainToPresentationMapper<T>): T

    data class Success(private val usersData: List<UserDomain>) : UsersDomain() {
        override fun <T> map(mapper: UsersDomainToPresentationMapper<T>): T = mapper.map(usersData)
    }

    data class Fail(private val errorType: ErrorType) : UsersDomain() {
        override fun <T> map(mapper: UsersDomainToPresentationMapper<T>): T = mapper.map(errorType)
    }

    object Empty : UsersDomain() {
        override fun <T> map(mapper: UsersDomainToPresentationMapper<T>) =
            mapper.map()
    }
}