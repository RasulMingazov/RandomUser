package com.jeanbernad.randomuser.data.user

import com.jeanbernad.randomuser.core.Abstract

interface UserData : Abstract.DataObject {
    fun <T> map(mapper: UserDataToDomainMapper<T>): T

    data class Success(
        private val fullName: String,
        private val fullAddress: String,
        private val gender: String,
        private val phone: String,
        private val mail: String,
        private val country: String,
        private val city: String,
        private val coordinates: String,
        private val birthday: String
    ) : UserData {
        override fun <T> map(mapper: UserDataToDomainMapper<T>) = mapper.map(
            fullName,
            fullAddress,
            gender,
            phone,
            mail,
            country,
            city,
            coordinates,
            birthday
        )
    }

    data class Fail(
        private val exception: Exception
    ) : UserData {
        override fun <T> map(mapper: UserDataToDomainMapper<T>) = mapper.map(exception)
    }
}