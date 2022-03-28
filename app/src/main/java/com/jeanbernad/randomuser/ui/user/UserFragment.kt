package com.jeanbernad.randomuser.ui.user

import android.content.ClipDescription
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.jeanbernad.randomuser.data.enteties.MinimalUser
import com.jeanbernad.randomuser.databinding.FragmentUserBinding
import com.jeanbernad.randomuser.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class UserFragment : Fragment() {
    private val viewModel: UserViewModel<MinimalUser> by viewModels()
    private var binding: FragmentUserBinding by autoCleared()
    private lateinit var userToString: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
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
                val coordinates = binding.coordinatesValue.text.removeSurrounding("(", ")").split(", ")
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
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, userToString)
                type = "text/plain"
            }
            Intent.createChooser(sendIntent, null).apply {
                startActivity(this)
            }
        }

        viewModel.user.observe(viewLifecycleOwner, {
            viewModel.bind(it,
                success = {
                    if (it.data != null) {
                        val user = it.data
                        binding.name.text = user.fullName
                        binding.birthdayDate.text = user.birthday
                        binding.addressName.text = user.fullAddress
                        binding.genderValue.text = user.gender
                        binding.phoneNumber.text = user.phone
                        binding.mailValue.text = user.mail
                        binding.countyName.text = user.country
                        binding.cityName.text = user.city
                        binding.coordinatesValue.text = user.coordinates
                        Glide.with(binding.root)
                                .load(user.picture)
                                .transform(CircleCrop())
                                .into(binding.avatar)

                        binding.progressBar.visibility = View.GONE
                        binding.container.visibility = View.VISIBLE

                        userToString = user.toString()
                    }
                    Log.d("UserFragment", "User upload success")
                },
                error = {
                    Log.d("UserFragment", "Mistake while user upload")
                },
                loading = {
                    binding.container.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                    Log.d("UserFragment", "User loading")
                }
            )
        })

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
            binding.swipeRefresh.isRefreshing = false
        }
    }
}