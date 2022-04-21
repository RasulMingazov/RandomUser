package com.jeanbernad.randomuser.data.user

import com.jeanbernad.randomuser.core.Abstract

interface UserData : Abstract.DataObject {
    fun <T> map(mapper: UserDataToDomainMapper<T>): T

    data class Success(
        private val id: String
    ) : UserData {
        override fun <T> map(mapper: UserDataToDomainMapper<T>) = mapper.map(id)
    }

    data class Fail(
        private val exception: Exception
    ) : UserData {
        override fun <T> map(mapper: UserDataToDomainMapper<T>) = mapper.map(exception)
    }
}