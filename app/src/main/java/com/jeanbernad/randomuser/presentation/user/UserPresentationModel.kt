package com.jeanbernad.randomuser.presentation.user

import com.jeanbernad.randomuser.core.Abstract

sealed class UserPresentationModel : Abstract.Object<Unit, UserPresentationModel.StringMapper> {
    override fun map(mapper: StringMapper) = Unit

    object Progress : UserPresentationModel()
    class Success(
        private val fullName: String,
        private val fullAddress: String,
        private val gender: String,
        private val phone: String,
        private val mail: String,
        private val country: String,
        private val city: String,
        private val coordinates: String,
        private val birthday: String
    ) : UserPresentationModel() {
        override fun map(mapper: StringMapper) = mapper.map(
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

    data class Fail(private val message: String) : UserPresentationModel() {
        override fun map(mapper: StringMapper) = mapper.map(message)
    }

    interface StringMapper : Abstract.Mapper {
        fun map(
            fullName: String,
            fullAddress: String,
            gender: String,
            phone: String,
            mail: String,
            country: String,
            city: String,
            coordinates: String,
            birthday: String
        ) {
            /*
            I need this function only for success state
             */
        }

        fun map(errorMessage: String) {
            /*
            I need this function only for fail state
             */
        }
    }

}