package com.jeanbernad.randomuser.presentation.user

import com.jeanbernad.randomuser.core.Abstract

interface ToUserValueMapper : Abstract.Mapper {
    fun map(
        fullName: String,
        fullAddress: String,
        gender: String,
        phone: String,
        mail: String,
        country: String,
        city: String,
        coordinates: String,
        birthday: String,
        image: String,
        thumbnail: String
    ) = Unit

    fun map(errorMessage: String) = Unit
}