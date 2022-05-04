package com.jeanbernad.randomuser.di.user

import android.content.Context
import com.jeanbernad.randomuser.data.user.BaseUserRepository
import com.jeanbernad.randomuser.data.user.ToUserMapper
import com.jeanbernad.randomuser.data.user.UserDataToDomainMapper
import com.jeanbernad.randomuser.data.user.local.ToUserLocalMapper
import com.jeanbernad.randomuser.data.user.local.UserLocalDataSource
import com.jeanbernad.randomuser.data.user.local.UserLocalModel
import com.jeanbernad.randomuser.data.user.remote.UserRemoteDataSource
import com.jeanbernad.randomuser.domain.ErrorDomainMapper
import com.jeanbernad.randomuser.domain.user.BaseUserDataToDomainMapper
import com.jeanbernad.randomuser.domain.user.UserDomain
import com.jeanbernad.randomuser.domain.user.UserRepository
import org.koin.core.module.Module
import org.koin.dsl.module

interface UserDataModuleProvider<T> {
    val userDataModule: T

    class Koin : UserDataModuleProvider<Module> {
        override val userDataModule: Module
            get() = module {
                fun provideToUserMapper() = ToUserMapper.Base()
                factory<ToUserMapper> { provideToUserMapper() }

                fun provideUserDataToDomainMapper(
                    errorDomainMapper: ErrorDomainMapper
                ) = BaseUserDataToDomainMapper(errorDomainMapper)
                factory<UserDataToDomainMapper<UserDomain>> { provideUserDataToDomainMapper(get()) }

                fun provideUserRepository(
                    userRemoteDataSource: UserRemoteDataSource,
                    userLocalDataSource: UserLocalDataSource,
                    toUserMapper: ToUserMapper,
                    toUserLocalMapper: ToUserLocalMapper<UserLocalModel>,
                    mapper: UserDataToDomainMapper<UserDomain>
                ) = BaseUserRepository(
                    userRemoteDataSource,
                    userLocalDataSource,
                    toUserMapper,
                    toUserLocalMapper,
                    mapper
                )
                single<UserRepository<UserDomain>> {
                    provideUserRepository(
                        get(),
                        get(),
                        get(),
                        get(),
                        get()
                    )
                }
            }
    }
}