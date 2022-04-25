package com.jeanbernad.randomuser.presentation.user

import android.content.ClipDescription
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import autoCleared
import com.jeanbernad.randomuser.core.ImageLoader
import com.jeanbernad.randomuser.databinding.FragmentUserBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

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
            Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${binding.phoneNumber.text}")
                startActivity(this)
            }
        }

        binding.mailBlock.setOnClickListener {
            Intent(Intent.ACTION_SEND).apply {
                type = ClipDescription.MIMETYPE_TEXT_PLAIN
                putExtra(Intent.EXTRA_EMAIL, arrayListOf("${binding.mailValue.text}"))
                startActivity(Intent.createChooser(this, ""))
            }
        }

        binding.coordinatesBlock.setOnClickListener {
            Intent(Intent.ACTION_VIEW).apply {
                val coordinates =
                    binding.coordinatesValue.text.removeSurrounding("(", ")").split(", ")
                data = Uri.parse(
                    String.format(
                        Locale.ENGLISH,
                        "geo:${coordinates[0]},${coordinates[1]}"
                    )
                )
                startActivity(this)
            }
        }

        binding.share.setOnClickListener {
            Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, userValue)
                type = "text/plain"
                startActivity(Intent.createChooser(this, null))
            }
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
                            binding.coordinatesValue.text = coordinates
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