package com.jeanbernad.randomuser.di.user

import com.jeanbernad.randomuser.di.app.AppDependencies
import com.jeanbernad.randomuser.di.vm.ViewModelModule
import com.jeanbernad.randomuser.presentation.user.UserFragment
import dagger.Component

@Component(
    dependencies = [AppDependencies::class],
    modules = [UserModule::class, ViewModelModule::class, UserBindModule::class]
)
@UserScope
interface UserComponent {

    fun inject(userFragment: UserFragment)

    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: AppDependencies): Builder
        fun build(): UserComponent
    }
}