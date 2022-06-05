package com.jeanbernad.randomuser.domain.user

import com.jeanbernad.randomuser.domain.user.all.UsersDomain


interface UserInteractor {
    suspend fun user(): UserDomain

    suspend fun users(): UsersDomain

    class Base(
        private val userRepository: UserRepository<UserDomain, UsersDomain>,
    ) : UserInteractor {
        override suspend fun user() = userRepository.user()
        override suspend fun users() = userRepository.users()
    }
}