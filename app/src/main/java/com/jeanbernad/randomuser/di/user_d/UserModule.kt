package com.jeanbernad.randomuser.di.user_d

import androidx.lifecycle.ViewModel
import com.jeanbernad.randomuser.di.ViewModelKey
import com.jeanbernad.randomuser.presentation.user.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class UserModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun userViewModel(viewModel: UserViewModel): ViewModel

}