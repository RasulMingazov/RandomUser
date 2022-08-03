package com.jeanbernad.randomuser.di.app

import android.content.Context
import androidx.annotation.RestrictTo
import com.jeanbernad.randomuser.data.user.local.UserDao
import com.jeanbernad.randomuser.data.user.remote.UserService
import com.jeanbernad.randomuser.domain.common.ErrorDomainMapper
import com.jeanbernad.randomuser.presentation.common.DateTimeFormat
import com.jeanbernad.randomuser.presentation.common.ErrorPresentationMapper
import com.jeanbernad.randomuser.presentation.common.TextOperation
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton
import kotlin.properties.Delegates

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent : AppDependencies {

    override val errorUiMapper: ErrorPresentationMapper
    override val errorDomainMapper: ErrorDomainMapper
    override val dateTimeFormat: DateTimeFormat
    override val textOperation: TextOperation
    override val userService: UserService
    override val userDao: UserDao

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}

interface AppDependencies {
    val errorUiMapper: ErrorPresentationMapper
    val errorDomainMapper: ErrorDomainMapper
    val dateTimeFormat: DateTimeFormat
    val textOperation: TextOperation
    val userService: UserService
    val userDao: UserDao
}

interface AppDependenciesProvider {
    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val dependencies: AppDependencies

    companion object : AppDependenciesProvider by AppDependenciesStore
}

object AppDependenciesStore : AppDependenciesProvider {
    override var dependencies: AppDependencies by Delegates.notNull()
}