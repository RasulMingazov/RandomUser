package com.jeanbernad.randomuser.presentation.user

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import autoCleared
import com.jeanbernad.randomuser.core.ImageLoader
import com.jeanbernad.randomuser.core.navigation.MailNavigator
import com.jeanbernad.randomuser.core.navigation.MapsNavigator
import com.jeanbernad.randomuser.core.navigation.PhoneNavigator
import com.jeanbernad.randomuser.core.navigation.ShareNavigator
import com.jeanbernad.randomuser.databinding.FragmentUserBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFragment : Fragment() {
    private val viewModel: UserViewModel by viewModel()
    private var binding by autoCleared<FragmentUserBinding>()
    private lateinit var userValue: String

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