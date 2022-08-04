package com.jeanbernad.randomuser.presentation.common

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import coil.ImageLoader
import coil.request.ImageRequest
import coil.target.ImageViewTarget


interface LoaderImage {

    fun load(
        image: String,
        imageView: ImageView,
        start: () -> Unit,
        success: () -> Unit
    )

    fun load(
        image: String,
        imageView: ImageView
    )

    interface CoilSettings {
        fun baseBuilder(image: String): ImageRequest.Builder
    }

    class Coil(
        private val context: Context,
        private val imageLoader: ImageLoader
    ) : LoaderImage, CoilSettings {

        override fun baseBuilder(image: String) = ImageRequest.Builder(context)
            .data(image)

        override fun load(
            image: String,
            imageView: ImageView,
            start: () -> Unit,
            success: () -> Unit,
        ) {
            imageLoader.enqueue(
                baseBuilder(image)
                    .target(object : ImageViewTarget(imageView) {
                        override fun onStart(owner: LifecycleOwner) {
                            super.onStart(owner)
                            start.invoke()
                        }

                        override fun onSuccess(result: Drawable) {
                            super.onSuccess(result)
                            success.invoke()
                        }
                    })
                    .build()
            )
        }

        override fun load(image: String, imageView: ImageView) {
            imageLoader.enqueue(
                baseBuilder(image)
                    .target(imageView)
                    .build()
            )
        }
    }
}