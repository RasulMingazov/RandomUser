package com.jeanbernad.randomuser.di.app_d

import android.content.Context
import androidx.annotation.RestrictTo
import com.jeanbernad.randomuser.core.ResourceProvider
import com.jeanbernad.randomuser.data.user.local.UserDao
import com.jeanbernad.randomuser.data.user.remote.UserService
import com.jeanbernad.randomuser.presentation.ErrorPresentationMapper
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton
import kotlin.properties.Delegates

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent : AppDeps {

    override val userService: UserService
    override val errorUiMapper: ErrorPresentationMapper
    override val userDao: UserDao

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}

interface AppDeps {
    val userService: UserService
    val errorUiMapper: ErrorPresentationMapper
    val userDao: UserDao
}

interface AppDepsProvider {
    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: AppDeps

    companion object : AppDepsProvider by AppDepsStore
}

object AppDepsStore : AppDepsProvider {
    override var deps: AppDeps by Delegates.notNull()
}