package com.jeanbernad.randomuser.di.app

import android.content.Context
import com.jeanbernad.randomuser.core.ResourceProvider
import com.jeanbernad.randomuser.domain.ErrorDomainMapper
import org.koin.core.module.Module
import org.koin.dsl.module

interface AppModuleProvider<T> {
    val appModule: T

    class Koin(private val context: Context) : AppModuleProvider<Module> {
        override val appModule: Module
            get() = module {
                fun provideResourceManager(context: Context): ResourceProvider =
                    ResourceProvider.Base(context)
                factory { provideResourceManager(get()) }

                fun provideErrorDomainMapper() =
                    ErrorDomainMapper.Base()
                factory<ErrorDomainMapper> { provideErrorDomainMapper() }
            }
    }
}