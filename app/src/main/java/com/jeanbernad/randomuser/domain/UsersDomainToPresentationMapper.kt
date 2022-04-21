package com.jeanbernad.randomuser.domain

import com.jeanbernad.randomuser.core.Abstract

interface UsersDomainToPresentationMapper<T> : Abstract.Mapper {
    fun map(usersDomain: List<UserDomain>): T
    fun map(errorType: ErrorType): T
}