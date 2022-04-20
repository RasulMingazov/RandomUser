package com.jeanbernad.randomuser.data

import com.jeanbernad.randomuser.core.Abstract

interface UserData : Abstract.DataObject {
    fun <T> map(mapper: UserDataToDomainMapper<T>): T

    data class Base(
        private val id: String,
    ) : UserData {
        override fun <T> map(mapper: UserDataToDomainMapper<T>): T = mapper.map(id)
    }
}
