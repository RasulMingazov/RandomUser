package com.jeanbernad.randomuser.presentation.user

import com.jeanbernad.randomuser.core.Abstract

sealed class UserPresentationModel : Abstract.PresentationObject<Unit, ToUserValueMapper> {
    override fun map(mapper: ToUserValueMapper) = Unit

    override fun textValue() = String()

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
        private val birthday: String,
        private val image: String
    ) : UserPresentationModel() {

        override fun textValue() =
            "${fullName}\n${birthday}\n${gender}\n${phone}\n${mail}\n${country}\n${city}\n${fullAddress}\n${image}"

        override fun map(mapper: ToUserValueMapper) = mapper.map(
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

    data class Fail(private val message: String) : UserPresentationModel() {
        override fun map(mapper: ToUserValueMapper) = mapper.map(message)
    }
}