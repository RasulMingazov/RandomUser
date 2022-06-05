package com.jeanbernad.randomuser.domain.user

interface UserRepository<T, M> {
    suspend fun user(): T

    suspend fun users(): M
}