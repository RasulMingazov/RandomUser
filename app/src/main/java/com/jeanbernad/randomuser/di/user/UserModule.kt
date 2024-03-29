package com.jeanbernad.randomuser.di.user

import androidx.lifecycle.ViewModel
import com.jeanbernad.randomuser.di.vm.ViewModelKey
import com.jeanbernad.randomuser.presentation.user.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class UserModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel.Base::class)
    abstract fun userViewModel(viewModel: UserViewModel.Base): ViewModel

}