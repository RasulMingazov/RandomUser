package com.jeanbernad.randomuser.domain

import com.jeanbernad.randomuser.data.UserRepository

interface UsersInteractor {
    suspend fun user(): UsersDomain

    class Base(
        private val userRepository: UserRepository<UsersDomain>,
    ) : UsersInteractor {
        override suspend fun user() = userRepository.user()
    }
}