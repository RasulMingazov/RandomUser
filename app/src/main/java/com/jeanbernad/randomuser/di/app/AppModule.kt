package com.jeanbernad.randomuser.di.app

import android.content.Context
import com.jeanbernad.randomuser.core.ResourceProvider
import com.jeanbernad.randomuser.domain.common.ErrorDomainMapper
import com.jeanbernad.randomuser.presentation.common.ErrorPresentationMapper
import dagger.Module
import dagger.Provides

@Module(includes = [PresentationModule::class, RemoteModule::class, DatabaseModule::class])
class AppModule {

    @Provides
    fun provideResourceManager(context: Context): ResourceProvider = ResourceProvider.Base(context)

    @Provides
    fun provideErrorPresentationMapper(resourceProvider: ResourceProvider): ErrorPresentationMapper =
        ErrorPresentationMapper.Base(resourceProvider)

    @Provides
    fun provideErrorDomainMapper(): ErrorDomainMapper = ErrorDomainMapper.Base()

}