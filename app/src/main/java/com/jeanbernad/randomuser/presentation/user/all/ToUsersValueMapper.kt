package com.jeanbernad.randomuser.presentation.user.all

import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.presentation.user.UserPresentationModel

interface ToUsersValueMapper : Abstract.Mapper {
    fun map(
     users: List<UserPresentationModel>
    ) = Unit

    fun map(errorMessage: String) = Unit

    fun map() = Unit
}