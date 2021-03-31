package com.dash.doorlite.presentation.restaurant.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.activity.OnBackPressedCallback
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dash.doorlite.R
import com.dash.doorlite.core.IIntent
import com.dash.doorlite.core.IState
import com.dash.doorlite.core.presentation.viewmodel.Location
import com.dash.doorlite.databinding.FragmentRestaurantsBinding
import com.dash.doorlite.domain.restaurant.model.Restaurants
import com.dash.doorlite.presentation.restaurant.viewmodel.RestaurantsViewModel
import com.dash.doorlite.presentation.scrollToTop
import dagger.hilt.android.AndroidEntryPoint


/**
 * Page State
 */
sealed class RestaurantsFragmentState : IState {
    object Idle : RestaurantsFragmentState()
    object Loading : RestaurantsFragmentState()
    data class NearbyRestaurants(val restaurants: Restaurants) :
            RestaurantsFragmentState()
}


/**
 * Page Intent
 */
sealed class RestaurantsFragmentIntent : IIntent {
    object Refresh : RestaurantsFragmentIntent()
    object ScrollList : RestaurantsFragmentIntent() // intent when the user scrolls

}


/**
 * The Entry point fragment, based on an MVI(Model, View, Intent) design pattern
 */
@AndroidEntryPoint
class RestaurantsFragment : Fragment() {
    private lateinit var binding: FragmentRestaurantsBinding
    private val viewModel by viewModels<RestaurantsViewModel>()
    private  lateinit var restaurantsAdapter: RestaurantsAdapter



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_restaurants, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        subscribe()
    }

    override fun onAttach(@NonNull context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

    }

    /**
     * the method initializes any UI component or makes init API calls
     */
    private fun initialize() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val location = arguments?.get("location")  as Location
        viewModel.getEateries(location)
        setupScrollListener()
        scrollToTop(binding.restaurantsList)

        binding.fab.setOnClickListener {
            scrollToTop(binding.restaurantsList)
        }


        restaurantsAdapter  = RestaurantsAdapter (sharedPref) {

            if(sharedPref.getString(it.id, "").equals("")){
                // store in pref
                with (sharedPref?.edit()) {
                    this?.putString(it.id, it.id)
                    this?.apply()
                }
            }else{
                //remove
                with (sharedPref?.edit()) {
                    this?.remove(it.id)
                    this?.apply()
                }
            }


        }
        binding.restaurantsList.adapter = restaurantsAdapter


    }


    /**
     * Subscribes to changes in the state flow and updates UI state
     */
    private fun subscribe() {

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is Restaurants.Success -> {
                    restaurantsAdapter.submitList(it.data.toMutableList())
                }
                else -> RestaurantsFragmentState.Idle
            }
        }

    }


    /**
     * Send an intent to the vm as list scrolled
     */
    private fun setupScrollListener() {
        val layoutManager = binding.restaurantsList.layoutManager as LinearLayoutManager
        binding.restaurantsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                binding.fab.visibility = View.VISIBLE
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                if (visibleItemCount + lastVisibleItem + 5 >= totalItemCount) {

                    viewModel.listScrolled()
                }
            }
        })
    }

}