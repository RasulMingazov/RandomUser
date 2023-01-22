package com.jeanbernad.randomuser.presentation.user

import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.databinding.BlockAvatarNameBinding
import com.jeanbernad.randomuser.databinding.BlockBirthdayGenderBinding
import com.jeanbernad.randomuser.databinding.BlockContactBinding
import com.jeanbernad.randomuser.databinding.BlockLocationBinding
import com.jeanbernad.randomuser.presentation.common.LoaderImage

interface UserPresentationBind : Abstract.PresentationModelBind {

    fun bind(
        mainBinding: BlockAvatarNameBinding,
        blockBirthdayGenderBinding: BlockBirthdayGenderBinding,
        contactBinding: BlockContactBinding,
        locationBinding: BlockLocationBinding
    )

    fun bindAvatar(
        loaderImage: LoaderImage,
        imageView: ImageView,
        start: () -> Unit,
        success: () -> Unit
    )

    fun bindError(errorTv: TextView, progressBar: ProgressBar)

    fun bindProgress(
        errorTv: TextView,
        refresh: SwipeRefreshLayout,
        progressBar: ProgressBar,
        shareIv: ImageView
    )
}