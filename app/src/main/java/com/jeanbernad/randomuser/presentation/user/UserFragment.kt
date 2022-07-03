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
import com.jeanbernad.randomuser.R
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
import com.jeanbernad.randomuser.presentation.user.all.ToUsersValueMapper
import com.jeanbernad.randomuser.presentation.user.all.UsersPresentationModel
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

        binding.blockContact.dataPhone.setOnClickListener {
            startActivity(PhoneNavigator.Base().intoPhone("${binding.blockContact.phoneValue.text}"))
        }

        binding.blockContact.dataMail.setOnClickListener {
            startActivity(MailNavigator.Base().intoMail("${binding.blockContact.mailValue.text}"), null)
        }

        binding.blockLocation.dataCoordinates.setOnClickListener {
            startActivity(
                MapsNavigator.Base().intoMaps(
                    binding.blockLocation.coordinatesValue.text.removeSurrounding("(", ")").split(", ")
                )
            )
        }

        binding.toolbar.share.setOnClickListener {
            startActivity(
                Intent.createChooser(ShareNavigator.Base().intoShare(userValue), null)
            )
        }

        binding.refresh.setOnRefreshListener {
            viewModel.refresh()
            binding.refresh.isRefreshing = false
        }

        viewModel.users.observe(
            viewLifecycleOwner
        ) {
            when (it) {
                is UsersPresentationModel.Empty -> Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT).show()
                is UsersPresentationModel.Base -> {
                    it.map(object: ToUsersValueMapper {
                        override fun map(users: List<UserPresentationModel>) {
                            super.map(users)
                            Toast.makeText(requireContext(),"AAA" + users.size, Toast.LENGTH_SHORT).show()
                        }
                    })
                }
                else -> {}
            }
        }

        viewModel.user.observe(
            viewLifecycleOwner
        ) {
            when (it) {
                is UserPresentationModel.Progress -> {
                    binding.error.visibility = View.GONE
                    binding.informationContainer.visibility = View.GONE
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
                            image: String,
                            thumbnail: String
                        ) {
                            binding.blockMainInformation.name.text = fullName
                            binding.blockLocation.addressValue.text = fullAddress
                            binding.blockLocation.coordinatesValue.text = coordinates
                            binding.blockBirthdayGender.genderValue.text = gender
                            binding.blockBirthdayGender.birthdayValue.text = birthday
                            binding.blockContact.phoneValue.text = phone
                            binding.blockContact.mailValue.text = mail
                            binding.blockLocation.countryValue.text = country
                            binding.blockLocation.cityValue.text = city
                            ImageLoader.BaseCircleGlide(image, thumbnail, Pair(
                                binding.blockMainInformation.avatar.width,
                                binding.blockMainInformation.avatar.height
                            ),
                                R.drawable.ic_person
                            ).load(binding.blockMainInformation.avatar)
                            binding.progressBar.visibility = View.GONE
                            binding.informationContainer.visibility = View.VISIBLE
                        }
                    })
                }
            }
        }
    }
}