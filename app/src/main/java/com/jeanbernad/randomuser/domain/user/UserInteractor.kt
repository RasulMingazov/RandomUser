package com.jeanbernad.randomuser.domain.user

import com.jeanbernad.randomuser.data.user.UserRepository


interface UserInteractor {
    suspend fun user(): UserDomain

    class Base(
        private val userRepository: UserRepository<UserDomain>,
    ) : UserInteractor {
        override suspend fun user() = userRepository.user()
    }
}