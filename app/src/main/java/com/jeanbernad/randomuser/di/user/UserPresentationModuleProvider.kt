package com.jeanbernad.randomuser.di.user

import com.jeanbernad.randomuser.core.ResourceProvider
import com.jeanbernad.randomuser.domain.user.UserDomainToPresentationMapper
import com.jeanbernad.randomuser.presentation.ErrorPresentationMapper
import com.jeanbernad.randomuser.presentation.user.BaseUserDomainToPresentationMapper
import com.jeanbernad.randomuser.presentation.user.UserPresentationModel
import com.jeanbernad.randomuser.presentation.user.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

interface UserPresentationModuleProvider<T> {

    val userPresentationModule: T

    class Koin : UserPresentationModuleProvider<Module> {
        override val userPresentationModule: Module
            get() = module {
                fun provideErrorPresentationMapper(resourceProvider: ResourceProvider) =
                    ErrorPresentationMapper.Base(resourceProvider)
                single<ErrorPresentationMapper> { provideErrorPresentationMapper(get()) }

                fun provideUserDomainToPresentationMapper(
                    errorPresentationMapper: ErrorPresentationMapper
                ) = BaseUserDomainToPresentationMapper(errorPresentationMapper)

                factory<UserDomainToPresentationMapper<UserPresentationModel>> {
                    provideUserDomainToPresentationMapper(get())
                }

                viewModel { UserViewModel(get(), get()) }
            }
    }
}