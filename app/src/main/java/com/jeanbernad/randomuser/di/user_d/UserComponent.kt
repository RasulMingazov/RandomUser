package com.jeanbernad.randomuser.di.user_d

import android.content.Context
import com.jeanbernad.randomuser.di.app_d.AppDeps
import com.jeanbernad.randomuser.presentation.user.UserFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [AppDeps::class],
    modules = [UserModule::class, ViewModelModule::class, UserProvideModule::class, UserBindModule::class]
)
@UserScope
interface UserComponent {

    fun inject(userFragment: UserFragment)

    @Component.Builder
    interface Builder {
        fun deps(deps: AppDeps): Builder
        fun build(): UserComponent
    }
}