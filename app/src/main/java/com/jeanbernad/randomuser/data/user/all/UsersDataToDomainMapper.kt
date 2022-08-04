package com.jeanbernad.randomuser.data.user.all

import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.data.user.UserData

interface UsersDataToDomainMapper<M> : Abstract.Mapper {
    fun map(
        users: List<UserData>
    ): M

    fun map(exception: Exception): M

    fun map(): M
}