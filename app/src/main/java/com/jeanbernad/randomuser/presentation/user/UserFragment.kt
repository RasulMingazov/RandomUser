package com.jeanbernad.randomuser.presentation.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jeanbernad.randomuser.core.autoCleared
import com.jeanbernad.randomuser.presentation.common.navigation.MailNavigator
import com.jeanbernad.randomuser.presentation.common.navigation.MapsNavigator
import com.jeanbernad.randomuser.presentation.common.navigation.PhoneNavigator
import com.jeanbernad.randomuser.presentation.common.navigation.ShareNavigator
import com.jeanbernad.randomuser.databinding.FragmentUserBinding
import com.jeanbernad.randomuser.di.app.AppDependenciesProvider
import com.jeanbernad.randomuser.di.vm.ViewModelFactory
import com.jeanbernad.randomuser.di.user.DaggerUserComponent
import com.jeanbernad.randomuser.presentation.common.LoaderImage
import com.jeanbernad.randomuser.presentation.user.all.ToUsersValueMapper
import javax.inject.Inject

class UserFragment : Fragment() {
    private var binding by autoCleared<FragmentUserBinding>()
    private lateinit var userValue: String

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[UserViewModel.Base::class.java]
    }

    @Inject
    lateinit var loaderImage: LoaderImage

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerUserComponent.builder().dependencies(AppDependenciesProvider.dependencies)
            .build().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.blockContact.dataPhone.setOnClickListener {
            startActivity(
                PhoneNavigator.Base().intoPhone("${binding.blockContact.phoneValue.text}")
            )
        }

        binding.blockContact.dataMail.setOnClickListener {
            startActivity(
                MailNavigator.Base().intoMail("${binding.blockContact.mailValue.text}"),
                null
            )
        }

        binding.blockLocation.dataCoordinates.setOnClickListener {
            startActivity(
                MapsNavigator.Base().intoMaps(
                    binding.blockLocation.coordinatesValue.text.removeSurrounding("(", ")")
                        .split(", ")
                )
            )
        }

        binding.toolbar.share.setOnClickListener {
            startActivity(
                Intent.createChooser(ShareNavigator.Base().intoShare(userValue), null)
            )
        }

        binding.refresh.setOnRefreshListener {
            viewModel.user()
            binding.refresh.isRefreshing = false
        }

        viewModel.observe(this) {
            when (it) {
                is UserPresentationModel.Progress -> {
                    binding.error.visibility = View.GONE
                    binding.refresh.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                is UserPresentationModel.Fail -> {
                    binding.progressBar.visibility = View.GONE
                    binding.error.visibility = View.VISIBLE
                    it.bindError(binding.error)
                }
                is UserPresentationModel.Success -> {
                    it.bindName(binding.blockMainInformation.name)
                    it.bindGender(binding.blockBirthdayGender.genderValue)
                    it.bindBirthday(binding.blockBirthdayGender.birthdayValue)
                    it.bindPhone(binding.blockContact.phoneValue)
                    it.bindMail(binding.blockContact.mailValue)
                    it.bindMail(binding.blockContact.mailValue)
                    it.bindCountry(binding.blockLocation.countryValue)
                    it.bindCity(binding.blockLocation.cityValue)
                    it.bindAddress(binding.blockLocation.addressValue)
                    it.bindCoordinates(binding.blockLocation.coordinatesValue)
                    it.bindAvatar(loaderImage, binding.blockMainInformation.avatar, {}, {
                        binding.progressBar.visibility = View.GONE
                        binding.refresh.visibility = View.VISIBLE
                    })
                    it.map(object : ToUserValueMapper {
                        override fun map(allValues: String) {
                            userValue = allValues
                        }
                    })
                }
            }
        }
        viewModel.user()
    }
}
