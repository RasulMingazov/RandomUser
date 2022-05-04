package com.jeanbernad.randomuser.di.user

import com.jeanbernad.randomuser.data.user.remote.UserRemoteDataSource
import com.jeanbernad.randomuser.data.user.remote.UserService
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

interface UserRemoteModuleProvider<T> {
    val userRemoteModule: T

    class Koin : UserRemoteModuleProvider<Module> {

        override val userRemoteModule: Module
            get() = module {
                fun provideUserService(retrofit: Retrofit) =
                    retrofit.create(UserService::class.java)
                factory { provideUserService(get()) }

                fun provideUserRemoteDataSource(
                    service: UserService
                ) = UserRemoteDataSource.Base(service)
                factory<UserRemoteDataSource> { provideUserRemoteDataSource(get()) }
            }
    }
}