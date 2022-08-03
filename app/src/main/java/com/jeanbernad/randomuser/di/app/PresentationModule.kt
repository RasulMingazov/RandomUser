package com.jeanbernad.randomuser.di.app

import com.jeanbernad.randomuser.presentation.common.DateTimeFormat
import com.jeanbernad.randomuser.presentation.common.TextOperation
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideDateTimeFormat(): DateTimeFormat = DateTimeFormat.Base()

    @Provides
    fun provideTextOperation(): TextOperation = TextOperation.Base()
}