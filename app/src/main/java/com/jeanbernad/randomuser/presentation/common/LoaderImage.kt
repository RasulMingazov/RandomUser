package com.jeanbernad.randomuser.presentation.common

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions

interface LoaderImage {

    fun load(image: String)

    class BaseCircleGlide(
        private val view: ImageView,
        private val thumbnail: String,
        private val size: Pair<Int, Int>,
        @DrawableRes private val placeholder: Int
    ) : LoaderImage {
        override fun load(image: String) {
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