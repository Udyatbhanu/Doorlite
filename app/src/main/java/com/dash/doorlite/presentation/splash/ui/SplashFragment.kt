package com.dash.doorlite.presentation.splash.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dash.doorlite.R
import com.dash.doorlite.core.presentation.viewmodel.Location
import com.dash.doorlite.databinding.FragmentSplashBinding
import com.dash.doorlite.presentation.splash.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint


sealed class SplashFragmentState {
    object Idle : SplashFragmentState()
    data class UserLocation(val location: Location) :
            SplashFragmentState()
}

//#FF3008
@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private val viewModel by viewModels<SplashViewModel>()
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val contextThemeWrapper: Context = ContextThemeWrapper(activity, R.style.NoActionBar)
        val localInflater = inflater.cloneInContext(contextThemeWrapper)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)

        activity?.let {
            Glide.with(it)
                    .asBitmap()
                    .load(SPLASH_IMAGE_URL)
                    .centerInside()
                    .into(binding.splashImg)
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe()
    }


    private fun subscribe() {

        viewModel.state.observe(viewLifecycleOwner) { it: SplashFragmentState? ->
            when (it) {
                is SplashFragmentState.UserLocation -> {
                    val bundle = bundleOf("location" to it.location)
                    Handler(Looper.getMainLooper()).postDelayed({ findNavController().navigate(R.id.restaurantsFragment, bundle) }, SPLASH_SCREEN_DELAY)

                }
                else -> binding.loadingTxt.visibility = View.VISIBLE
            }

        }
    }


    companion object {
        private const val SPLASH_SCREEN_DELAY = 3000L
        private const val SPLASH_IMAGE_URL = "https://hawkerfresh.s3.ap-south-1.amazonaws.com/doorlite/splash.png"
    }
}