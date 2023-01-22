package com.jeanbernad.randomuser.presentation.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import javax.inject.Inject

class UserFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[UserViewModel.Base::class.java]
    }

    private var binding by autoCleared<FragmentUserBinding>()

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

        viewModel.user()

        viewModel.observe(this) { user ->
            user.bind(
                binding.blockMainInformation,
                binding.blockBirthdayGender,
                binding.blockContact,
                binding.blockLocation
            )
            user.bindError(binding.error, binding.progressBar)
            user.bindAvatar(loaderImage, binding.blockMainInformation.avatar, {}, {
                binding.progressBar.visibility = View.GONE
                binding.refresh.visibility = View.VISIBLE
                binding.toolbar.share.isEnabled = true
            })
            user.bindProgress(
                binding.error,
                binding.refresh,
                binding.progressBar,
                binding.toolbar.share
            )
        }

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
                Intent.createChooser(ShareNavigator.Base().intoShare(viewModel.shareValues()), null)
            )
        }

        binding.refresh.setOnRefreshListener {
            viewModel.user()
            binding.refresh.isRefreshing = false
        }
    }
}
