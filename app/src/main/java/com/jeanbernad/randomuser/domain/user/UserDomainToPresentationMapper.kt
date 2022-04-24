package com.jeanbernad.randomuser.domain.user

import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.core.ErrorType

interface UserDomainToPresentationMapper<T> : Abstract.Mapper {
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
        image: String
    ): T

    fun map(errorType: ErrorType): T
}