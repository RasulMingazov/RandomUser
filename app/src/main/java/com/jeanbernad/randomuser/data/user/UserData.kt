package com.jeanbernad.randomuser.data.user

import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.data.user.local.ToUserLocalMapper

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
        private val birthday: String,
        private val image: String,
    ) : UserData, ToLocalMap {
        override fun <T> map(mapper: UserDataToDomainMapper<T>) = mapper.map(
            fullName,
            fullAddress,
            gender,
            phone,
            mail,
            country,
            city,
            coordinates,
            birthday,
            image
        )

        override fun <P> mapToLocal(mapper: ToUserLocalMapper<P>) = mapper.mapToLocal(
            fullName,
            fullAddress,
            gender,
            phone,
            mail,
            country,
            city,
            coordinates,
            birthday,
            image
        )
    }

    data class Fail(
        private val exception: Exception
    ) : UserData {
        override fun <T> map(mapper: UserDataToDomainMapper<T>) = mapper.map(exception)
    }
}

interface ToLocalMap {
    fun <P> mapToLocal(mapper: ToUserLocalMapper<P>): P
}