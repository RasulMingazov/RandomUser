package com.jeanbernad.randomuser.domain.user


interface UserInteractor {
    suspend fun user(): UserDomain

    class Base(
        private val userRepository: UserRepository<UserDomain>,
    ) : UserInteractor {
        override suspend fun user() = userRepository.user()
    }
}