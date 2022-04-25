package com.jeanbernad.randomuser.core

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

interface ImageLoader {

    fun load(view: ImageView)

    class BaseGlide(
        private val image: String,
    ) : ImageLoader {
        override fun load(view: ImageView) {
            Glide.with(view.context)
                .load(image)
                .transform(CircleCrop())
                .into(view)
        }
    }
}