package com.jeanbernad.randomuser.presentation.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jeanbernad.randomuser.presentation.common.ImageLoader
import com.jeanbernad.randomuser.core.autoCleared
import com.jeanbernad.randomuser.presentation.common.navigation.MailNavigator
import com.jeanbernad.randomuser.presentation.common.navigation.MapsNavigator
import com.jeanbernad.randomuser.presentation.common.navigation.PhoneNavigator
import com.jeanbernad.randomuser.presentation.common.navigation.ShareNavigator
import com.jeanbernad.randomuser.databinding.FragmentUserBinding
import com.jeanbernad.randomuser.di.app.AppDependenciesProvider
import com.jeanbernad.randomuser.di.vm.ViewModelFactory
import com.jeanbernad.randomuser.di.user.DaggerUserComponent
import javax.inject.Inject

class UserFragment : Fragment() {
    private var binding by autoCleared<FragmentUserBinding>()
    private lateinit var userValue: String

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[UserViewModel::class.java]
    }

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

        binding.phoneBlock.setOnClickListener {
            startActivity(PhoneNavigator.Base().intoPhone("${binding.phoneNumber.text}"))
        }

        binding.mailBlock.setOnClickListener {
            startActivity(MailNavigator.Base().intoMail("${binding.mailValue.text}"), null)
        }

        binding.coordinatesBlock.setOnClickListener {
            startActivity(
                MapsNavigator.Base().intoMaps(
                    binding.coordinatesValue.text.removeSurrounding("(", ")").split(", ")
                )
            )
        }

        binding.share.setOnClickListener {
            startActivity(
                Intent.createChooser(ShareNavigator.Base().intoShare(userValue), null)
            )
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
            binding.swipeRefresh.isRefreshing = false
        }

        viewModel.user.observe(
            viewLifecycleOwner
        ) {
            when (it) {
                is UserPresentationModel.Progress -> {
                    binding.error.visibility = View.GONE
                    binding.container.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                is UserPresentationModel.Fail -> {
                    it.map(object : ToUserValueMapper {
                        override fun map(errorMessage: String) {
                            binding.progressBar.visibility = View.GONE
                            binding.error.visibility = View.VISIBLE
                            binding.error.text = errorMessage
                        }
                    })
                }
                is UserPresentationModel.Success -> {
                    userValue = it.textValue()
                    it.map(object : ToUserValueMapper {
                        override fun map(
                            fullName: String,
                            fullAddress: String,
                            gender: String,
                            phone: String,
                            mail: String,
                            country: String,
                            city: String,
                            coordinates: String,
                            birthday: String,
                            image: String
                        ) {
                            binding.name.text = fullName
                            binding.addressName.text = fullAddress
                            binding.coordinatesValue.text = coordinates
                            binding.genderValue.text = gender
                            binding.birthdayDate.text = birthday
                            binding.phoneNumber.text = phone
                            binding.mailValue.text = mail
                            binding.countyName.text = country
                            binding.cityName.text = city
                            ImageLoader.BaseGlide(image).load(binding.avatar)
                            binding.progressBar.visibility = View.GONE
                            binding.container.visibility = View.VISIBLE
                        }
                    })
                }
            }
        }
    }
}