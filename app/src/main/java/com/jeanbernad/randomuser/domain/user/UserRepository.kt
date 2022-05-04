package com.jeanbernad.randomuser.domain.user

interface UserRepository<T> {
    suspend fun user(): T
}