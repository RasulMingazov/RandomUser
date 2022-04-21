package com.jeanbernad.randomuser.domain.user

import com.jeanbernad.randomuser.domain.ErrorType

sealed class UserDomain {

    abstract fun <T> map(mapper: UserDomainToPresentationMapper<T>): T

    data class Success(private val id: String) : UserDomain() {
        override fun <T> map(mapper: UserDomainToPresentationMapper<T>): T = mapper.map(id)
    }

    data class Fail(private val errorType: ErrorType) : UserDomain() {
        override fun <T> map(mapper: UserDomainToPresentationMapper<T>): T = mapper.map(errorType)
    }
}