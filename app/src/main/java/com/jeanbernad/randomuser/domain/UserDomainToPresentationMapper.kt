package com.jeanbernad.randomuser.domain

import com.jeanbernad.randomuser.core.Abstract

interface UserDomainToPresentationMapper<T> : Abstract.Mapper {
    fun map(id: String): T

    fun map(errorType: ErrorType): T
}