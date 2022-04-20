package com.jeanbernad.randomuser.domain

data class UserDomain(
    private val id: String
) {
    fun <T> map(mapper: UserDomainToPresentationMapper<T>) = mapper.map(id)
}