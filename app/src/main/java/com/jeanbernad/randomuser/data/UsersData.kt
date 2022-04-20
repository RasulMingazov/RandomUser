package com.jeanbernad.randomuser.data

import com.jeanbernad.randomuser.core.Abstract

sealed class UsersData : Abstract.DataObject {

    abstract fun <T> map(mapper: UsersDataToDomainMapper<T>): T

    data class Success(private val users: List<UserData>) : UsersData() {
        override fun <T> map(mapper: UsersDataToDomainMapper<T>): T {
            return mapper.map(users)
        }
    }

    data class Fail(private val exception: Exception) : UsersData() {
        override fun <T> map(mapper: UsersDataToDomainMapper<T>): T {
            return mapper.map(exception)
        }
    }
}