package com.jeanbernad.randomuser.presentation.user

import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.databinding.*
import com.jeanbernad.randomuser.presentation.common.LoaderImage

sealed class UserPresentationModel : UserPresentationBind,
    Abstract.PresentationObject<Unit, ToUserValueMapper> {

    override fun map(mapper: ToUserValueMapper) = Unit

    override fun bind(
        mainBinding: BlockAvatarNameBinding,
        blockBirthdayGenderBinding: BlockBirthdayGenderBinding,
        contactBinding: BlockContactBinding,
        locationBinding: BlockLocationBinding
    ) = Unit

    override fun bindAvatar(
        loaderImage: LoaderImage,
        imageView: ImageView,
        start: () -> Unit,
        success: () -> Unit
    ) = Unit

    override fun bindProgress(
        errorTv: TextView,
        refresh: SwipeRefreshLayout,
        progressBar: ProgressBar,
        shareIv: ImageView
    ) = Unit

    override fun bindError(errorTv: TextView, progressBar: ProgressBar) = Unit

    object Progress : UserPresentationModel() {
        override fun bindProgress(
            errorTv: TextView,
            refresh: SwipeRefreshLayout,
            progressBar: ProgressBar,
            shareIv: ImageView
        ) {
            errorTv.isVisible = false
            refresh.isVisible = false
            progressBar.isVisible = true
            shareIv.isEnabled = false
        }
    }

    data class Fail(private val message: String) : UserPresentationModel() {
        override fun bindError(errorTv: TextView, progressBar: ProgressBar) {
            errorTv.text = message
            errorTv.isVisible = true
            progressBar.isVisible = false
        }
    }

    class Success(
        private val fullName: String,
        private val fullAddress: String,
        private val gender: String,
        private val phone: String,
        private val mail: String,
        private val country: String,
        private val city: String,
        private val coordinates: String,
        private val birthday: String,
        private val image: String,
        private val allValues: String
    ) : UserPresentationModel() {

        override fun bind(
            mainBinding: BlockAvatarNameBinding,
            blockBirthdayGenderBinding: BlockBirthdayGenderBinding,
            contactBinding: BlockContactBinding,
            locationBinding: BlockLocationBinding
        ) {
            mainBinding.name.text = fullName
            blockBirthdayGenderBinding.genderValue.text = gender
            blockBirthdayGenderBinding.birthdayValue.text = birthday
            contactBinding.phoneValue.text = phone
            contactBinding.mailValue.text = mail
            locationBinding.countryValue.text = country
            locationBinding.cityValue.text = city
            locationBinding.addressValue.text = fullAddress
            locationBinding.coordinatesValue.text = coordinates
        }

        override fun bindAvatar(
            loaderImage: LoaderImage,
            imageView: ImageView,
            start: () -> Unit,
            success: () -> Unit
        ) {
            loaderImage.load(this.image, imageView, start, success)
        }

        override fun map(mapper: ToUserValueMapper) = mapper.map(allValues)
    }
}