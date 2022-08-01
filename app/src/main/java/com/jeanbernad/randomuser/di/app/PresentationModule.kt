package com.jeanbernad.randomuser.di.app

import com.jeanbernad.randomuser.presentation.common.DateTimeFormat
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideDateTimeFormat(): DateTimeFormat = DateTimeFormat.Base()
}