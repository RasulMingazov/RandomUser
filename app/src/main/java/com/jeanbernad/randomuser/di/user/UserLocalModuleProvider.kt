package com.jeanbernad.randomuser.di.user

import android.content.Context
import com.jeanbernad.randomuser.data.user.local.*
import org.koin.core.module.Module
import org.koin.dsl.module

interface UserLocalModuleProvider<T> {
    val userLocalModule: T

    class Koin : UserLocalModuleProvider<Module> {
        override val userLocalModule: Module
            get() = module {

                fun provideUserDatabase(context: Context) = UserDatabase.BaseRoom(context)
                single<UserDatabase> { provideUserDatabase(get()) }

                fun provideUserDao(userDatabase: UserDatabase) = userDatabase.userDao()
                factory { provideUserDao(get()) }

                fun provideToUserLocalMapper() = ToUserLocalMapper.Base()
                factory<ToUserLocalMapper<UserLocalModel>> { provideToUserLocalMapper() }

                fun provideLocalDataSource(userDao: UserDao) = UserLocalDataSource.Base(userDao)
                factory<UserLocalDataSource> { provideLocalDataSource(get()) }
            }
    }
}