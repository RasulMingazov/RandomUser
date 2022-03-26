package com.jeanbernad.randomuser.ui.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.jeanbernad.randomuser.data.enteties.User
import com.jeanbernad.randomuser.databinding.FragmentUserBinding
import com.jeanbernad.randomuser.extensions.*
import com.jeanbernad.randomuser.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment() {
    private val viewModel: UserViewModel<User> by viewModels()
    private var binding: FragmentUserBinding by autoCleared()

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

        viewModel.user.observe(viewLifecycleOwner, {
            viewModel.bind(it,
                    success = {
                        val user = it.data!!
                        Glide.with(binding.root)
                                .load(user.results[0].picture.medium)
                                 .transform(CircleCrop())
                                .into(binding.avatar)
                        binding.name.text = user.fullName()
                        binding.birthdayDate.text = user.birthday()
                        binding.addressName.text = user.fullAddress()
                        binding.genderValue.text = user.gender()
                        binding.phoneNumber.text = user.phone()
                        binding.mailValue.text = user.mail()
                        binding.countyName.text = user.country()
                        binding.cityName.text = user.city()
                        binding.progressBar.visibility = View.GONE
                        binding.container.visibility = View.VISIBLE
                        Log.d("UserFragment", "User upload success")
                    },
                    error = {
                        Toast.makeText(requireContext(), "Some problems, try later", Toast.LENGTH_SHORT).show()
                        Log.d("UserFragment", "Mistake while user upload")
                    },
                    loading = {
                        binding.progressBar.visibility = View.VISIBLE
                        Log.d("UserFragment", "User loading")
                    }
            )
        })
    }
}