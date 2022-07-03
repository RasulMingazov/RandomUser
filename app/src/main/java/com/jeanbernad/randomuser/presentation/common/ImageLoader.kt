package com.jeanbernad.randomuser.presentation.common

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.Placeholder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

interface ImageLoader {

    fun load(view: ImageView)

    class BaseCircleGlide(
        private val image: String,
        private val thumbnail: String,
        private val size: Pair<Int, Int>,
        @DrawableRes private val placeholder: Int
    ) : ImageLoader {
        override fun load(view: ImageView) {
            Glide.with(view.context)
                .load(image)
                .thumbnail(Glide.with(view.context).load(thumbnail))
                .placeholder(placeholder)
                .apply(
                    RequestOptions()
                        .fitCenter()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .override(size.first, size.second)
                )
                .transform(CircleCrop())
                .into(view)
        }
    }
}