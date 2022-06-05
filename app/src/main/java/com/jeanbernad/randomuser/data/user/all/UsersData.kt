package com.jeanbernad.randomuser.data.user.all

import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.data.user.UserData

sealed class UsersData : Abstract.DataObject {

    abstract fun <M> map(mapper: UsersDataToDomainMapper<M>): M

    data class Success(private val users: List<UserData>) : UsersData() {
        override fun <M> map(mapper: UsersDataToDomainMapper<M>): M {
            return mapper.map(users)
        }
    }

    object Empty : UsersData() {
        override fun <M> map(mapper: UsersDataToDomainMapper<M>): M {
            return mapper.map()
        }
    }

    data class Fail(private val exception: Exception) : UsersData() {
        override fun <M> map(mapper: UsersDataToDomainMapper<M>): M {
            return mapper.map(exception)
        }
    }
}