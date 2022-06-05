package com.jeanbernad.randomuser.di

import androidx.lifecycle.ViewModelProvider
import com.jeanbernad.randomuser.di.app_d.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}