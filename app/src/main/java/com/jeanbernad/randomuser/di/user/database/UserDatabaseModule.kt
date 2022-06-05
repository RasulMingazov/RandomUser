package com.jeanbernad.randomuser.di.user.database

import android.content.Context
import com.jeanbernad.randomuser.data.user.local.UserDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserDatabaseModule {

    @Provides
    @Singleton
    fun provideUserDatabase(context: Context): UserDatabase = UserDatabase.BaseRoom(context)

    @Provides
    @Singleton
    fun provideUserDao(database: UserDatabase) = database.userDao()
}