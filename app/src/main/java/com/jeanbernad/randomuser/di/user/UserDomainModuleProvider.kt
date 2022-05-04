package com.jeanbernad.randomuser.di.user

import com.jeanbernad.randomuser.domain.user.UserDomain
import com.jeanbernad.randomuser.domain.user.UserInteractor
import com.jeanbernad.randomuser.domain.user.UserRepository
import org.koin.core.module.Module
import org.koin.dsl.module

interface UserDomainModuleProvider<T> {

    val userDomainModule: T

    class Koin : UserDomainModuleProvider<Module> {
        override val userDomainModule: Module
            get() = module {
                fun provideUserInteractor(
                    userRepository: UserRepository<UserDomain>
                ) = UserInteractor.Base(userRepository)

                factory<UserInteractor> { provideUserInteractor(get()) }
            }
    }
}