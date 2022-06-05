package com.jeanbernad.randomuser.di.app_d

import android.content.Context
import com.jeanbernad.randomuser.core.ResourceProvider
import com.jeanbernad.randomuser.data.user.local.UserDao
import com.jeanbernad.randomuser.data.user.local.UserDatabase
import com.jeanbernad.randomuser.presentation.ErrorPresentationMapper
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class AppModule {

    @Provides
    fun provideResourceManager(context: Context): ResourceProvider = ResourceProvider.Base(context)

    @Provides
    fun provideErrorPresentationMapper(resourceProvider: ResourceProvider): ErrorPresentationMapper =
        ErrorPresentationMapper.Base(resourceProvider)

    @Provides
    fun provideUserDatabase(context: Context): UserDatabase = UserDatabase.BaseRoom(context)

    @Provides
    fun provideUserDao(userDatabase: UserDatabase): UserDao = userDatabase.userDao()
}