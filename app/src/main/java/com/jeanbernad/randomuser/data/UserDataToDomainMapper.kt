package com.jeanbernad.randomuser.data

import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.domain.UserDomain

interface UserDataToDomainMapper<T> : Abstract.Mapper {
    fun map(id: String): T
}

class BaseUserDataToDomainMapper : UserDataToDomainMapper<UserDomain> {
    override fun map(id: String) = UserDomain(id)
}