package com.dash.doorlite.presentation.restaurant_details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dash.doorlite.R
import com.dash.doorlite.core.IIntent
import com.dash.doorlite.core.IState
import com.dash.doorlite.databinding.FragmentRestaurantDetailsBinding
import com.dash.doorlite.domain.restaurant.model.Restaurant
import com.dash.doorlite.domain.restaurant.model.Restaurants
import com.dash.doorlite.domain.restaurant_details.model.Details
import com.dash.doorlite.presentation.restaurant.ui.RestaurantsFragmentIntent
import com.dash.doorlite.presentation.restaurant.ui.RestaurantsFragmentState
import com.dash.doorlite.presentation.restaurant_details.viewmodel.RestaurantDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


sealed class RestaurantsDetailsFragmentState : IState {
    object Idle : RestaurantsDetailsFragmentState()
    object Loading : RestaurantsDetailsFragmentState()
    object Error : RestaurantsDetailsFragmentState()
    data class RestaurantDetails(val details: Details) : RestaurantsDetailsFragmentState()

}

sealed class RestaurantsDetailsFragmentIntent : IIntent {
    data class GetRestaurantDetails(val id: String) :
            RestaurantsDetailsFragmentIntent()

}


@AndroidEntryPoint
class RestaurantDetailsFragment : Fragment() {
    private lateinit var binding: FragmentRestaurantDetailsBinding
    private val viewModel by viewModels<RestaurantDetailsViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_restaurant_details,
                container,
                false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        subscribe()
    }


    private fun initialize() {
        (activity as? AppCompatActivity)?.supportActionBar?.show()
        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        binding.toolbar.setNavigationOnClickListener {
            Navigation.findNavController(binding.root).popBackStack()
        }


        val restaurant = arguments?.get("restaurant") as Restaurant


        println("Popular items ${restaurant.popularItems}")
        binding.restaurant = restaurant as Restaurant?
        Glide.with(binding.root.context)
                .asBitmap()
                .load(restaurant.headerImage)
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(16))
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.placeholder))
                .diskCacheStrategy(DiskCacheStrategy.ALL)

                .into(binding.image)


        lifecycleScope.launch {
            viewModel.intents.send(RestaurantsDetailsFragmentIntent.GetRestaurantDetails(restaurant.id))
        }


    }

    private fun subscribe() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is RestaurantsDetailsFragmentState.RestaurantDetails -> {
                    binding.details = it.details
                }

            }
        }
    }
}