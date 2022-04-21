package com.jeanbernad.randomuser.domain.user

import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.domain.ErrorType

interface UserDomainToPresentationMapper<T> : Abstract.Mapper {
    fun map(id: String): T

    fun map(errorType: ErrorType): T
}