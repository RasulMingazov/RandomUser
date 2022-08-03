package com.jeanbernad.randomuser.presentation.user

import android.widget.TextView
import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.presentation.common.TextOperation

sealed class UserPresentationModel : UserUiBind, Abstract.PresentationObject<Unit, ToUserValueMapper> {

    override fun map(mapper: ToUserValueMapper) = Unit

    override fun bind(
        name: TextView,
        gender: TextView,
        birthday: TextView,
        phone: TextView,
        mail: TextView,
        country: TextView,
        city: TextView,
        address: TextView,
        coordinates: TextView
    ) = Unit

    override fun bind(error: TextView) = Unit

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
        private val image: String,
        private val thumbnail: String,
        private val allValues: String
    ) : UserPresentationModel() {

        override fun bind(
            name: TextView,
            gender: TextView,
            birthday: TextView,
            phone: TextView,
            mail: TextView,
            country: TextView,
            city: TextView,
            address: TextView,
            coordinates: TextView
        ) {
            name.text = this.fullName
            gender.text = this.gender
            birthday.text = this.birthday
            phone.text = this.phone
            mail.text = this.mail
            country.text = this.country
            city.text = this.city
            address.text = this.fullAddress
            coordinates.text = this.coordinates
        }

        override fun map(mapper: ToUserValueMapper) = mapper.map(allValues)
    }

    data class Fail(private val message: String) : UserPresentationModel() {
        override fun bind(error: TextView) {
            error.text = message
        }
    }
}