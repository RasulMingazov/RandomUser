package com.jeanbernad.randomuser.presentation.user

import android.widget.ImageView
import android.widget.TextView
import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.presentation.common.LoaderImage

sealed class UserPresentationModel : UserUiBind,
    Abstract.PresentationObject<Unit, ToUserValueMapper> {

    override fun map(mapper: ToUserValueMapper) = Unit

    override fun bindName(name: TextView) = Unit
    override fun bindGender(gender: TextView) = Unit
    override fun bindBirthday(birthday: TextView) = Unit
    override fun bindPhone(phone: TextView) = Unit
    override fun bindMail(mail: TextView) = Unit
    override fun bindCountry(country: TextView) = Unit
    override fun bindCity(city: TextView) = Unit
    override fun bindAddress(address: TextView) = Unit
    override fun bindCoordinates(coordinates: TextView) = Unit
    override fun bindAvatar(
        loaderImage: LoaderImage,
        imageView: ImageView,
        start: () -> Unit,
        success: () -> Unit
    ) = Unit

    override fun bindError(error: TextView) = Unit

    object Progress : UserPresentationModel()

    data class Fail(private val message: String) : UserPresentationModel() {
        override fun bindError(error: TextView) {
            error.text = message
        }
    }

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
        private val allValues: String
    ) : UserPresentationModel() {

        override fun bindName(name: TextView) {
            name.text = this.fullName
        }

        override fun bindGender(gender: TextView) {
            gender.text = this.gender
        }

        override fun bindPhone(phone: TextView) {
            phone.text = this.phone
        }

        override fun bindMail(mail: TextView) {
            mail.text = this.mail
        }

        override fun bindCountry(country: TextView) {
            country.text = this.country
        }

        override fun bindCity(city: TextView) {
            city.text = this.city
        }

        override fun bindAddress(address: TextView) {
            address.text = this.fullAddress
        }

        override fun bindCoordinates(coordinates: TextView) {
            coordinates.text = this.coordinates
        }

        override fun bindBirthday(birthday: TextView) {
            birthday.text = this.birthday
        }

        override fun bindAvatar(
            loaderImage: LoaderImage,
            imageView: ImageView,
            start: () -> Unit,
            success: () -> Unit
        ) {
            loaderImage.load(this.image, imageView, start, success)
        }

        override fun map(mapper: ToUserValueMapper) = mapper.map(allValues)
    }
}