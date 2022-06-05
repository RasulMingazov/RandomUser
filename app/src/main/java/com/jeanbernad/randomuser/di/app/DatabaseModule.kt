package com.jeanbernad.randomuser.di.app

import com.jeanbernad.randomuser.di.user.database.UserDatabaseModule
import dagger.Module

@Module(includes = [UserDatabaseModule::class])
class DatabaseModule