package com.jeanbernad.randomuser.presentation.user

import com.jeanbernad.randomuser.core.Abstract

interface ToUserValueMapper : Abstract.Mapper {
    fun map(
        allValues: String
    ) = Unit

    fun mapError(errorMessage: String) = Unit
}