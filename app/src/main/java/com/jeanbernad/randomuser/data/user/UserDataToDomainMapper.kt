package com.jeanbernad.randomuser.data.user

import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.core.ErrorType
import com.jeanbernad.randomuser.domain.user.UserDomain
import retrofit2.HttpException
import java.net.UnknownHostException

interface UserDataToDomainMapper<T> : Abstract.Mapper {
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

    fun map(exception: Exception): T
}