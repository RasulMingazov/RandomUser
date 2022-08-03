package com.jeanbernad.randomuser.di.app

import android.content.Context
import coil.ImageLoader
import com.jeanbernad.randomuser.presentation.common.DateTimeFormat
import com.jeanbernad.randomuser.core.TextOperation
import com.jeanbernad.randomuser.presentation.common.LoaderImage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresentationModule {

    @Provides
    fun provideDateTimeFormat(): DateTimeFormat = DateTimeFormat.Base()

    @Provides
    fun provideTextOperation(): TextOperation = TextOperation.Base()

    @Provides
    @Singleton
    fun provideCoil(context: Context): ImageLoader = ImageLoader.Builder(context).build()

    @Provides
    @Singleton
    fun provideCoilLoaderImage(context: Context, imageLoader: ImageLoader): LoaderImage = LoaderImage.Coil(context, imageLoader)
}