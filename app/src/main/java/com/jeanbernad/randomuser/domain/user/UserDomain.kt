package com.jeanbernad.randomuser.domain.user

import com.jeanbernad.randomuser.domain.ErrorType

sealed class UserDomain {

    abstract fun <T> map(mapper: UserDomainToPresentationMapper<T>): T

    data class Success(
        private val id: String,
        private val fullName: String,
        private val fullAddress: String,
        private val gender: String,
        private val phone: String,
        private val mail: String,
        private val country: String,
        private val city: String,
        private val coordinates: String,
        private val birthday: String
    ) : UserDomain() {
        override fun <T> map(mapper: UserDomainToPresentationMapper<T>): T = mapper.map(
            id,
            fullName,
            fullAddress,
            gender,
            phone,
            mail,
            country,
            city,
            coordinates,
            birthday)
    }

    data class Fail(private val errorType: ErrorType) : UserDomain() {
        override fun <T> map(mapper: UserDomainToPresentationMapper<T>): T = mapper.map(errorType)
    }
}