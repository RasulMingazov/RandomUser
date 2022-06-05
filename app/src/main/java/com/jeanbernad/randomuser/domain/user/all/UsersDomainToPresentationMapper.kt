package com.jeanbernad.randomuser.domain.user.all

import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.core.ErrorType
import com.jeanbernad.randomuser.domain.user.UserDomain

interface UsersDomainToPresentationMapper<T> : Abstract.Mapper {
    fun map(users: List<UserDomain>): T
    fun map(errorType: ErrorType): T
    fun map(): T
}