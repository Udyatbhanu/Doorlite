package com.dash.doorlite.presentation.restaurant_details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dash.doorlite.core.IModel
import com.dash.doorlite.domain.restaurant_details.GetRestaurantDetailsUseCase
import com.dash.doorlite.domain.restaurant_details.model.RestaurantDetails
import com.dash.doorlite.presentation.restaurant.ui.RestaurantsFragmentState
import com.dash.doorlite.presentation.restaurant_details.ui.RestaurantsDetailsFragmentIntent
import com.dash.doorlite.presentation.restaurant_details.ui.RestaurantsDetailsFragmentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RestaurantDetailsViewModel @Inject constructor(private val getRestaurantDetailsUseCase: GetRestaurantDetailsUseCase) :
        ViewModel(),
        IModel<RestaurantsDetailsFragmentState, RestaurantsDetailsFragmentIntent> {

    override val intents: Channel<RestaurantsDetailsFragmentIntent> = Channel(Channel.UNLIMITED)
    private val _state = MutableLiveData<RestaurantsDetailsFragmentState>().apply { value = RestaurantsDetailsFragmentState.Idle }
    val state: LiveData<RestaurantsDetailsFragmentState>
        get() = _state

    init {
        handlerIntent()
    }

    private fun handlerIntent() {
        // Lambda function to launchViewModelScope on a single thread dispatcher
        viewModelScope.launch {
            intents.consumeAsFlow().collect {
                when (it) {
                    is RestaurantsDetailsFragmentIntent.GetRestaurantDetails -> fetchDetails(it.id)
                    else -> RestaurantsFragmentState.Idle
                }

            }
        }

    }


    /**
     * Fetch the details
     */
    private suspend fun fetchDetails(id: String) {


        getRestaurantDetailsUseCase.getRestaurantDetails(id).collect {
            when (it) {
                is RestaurantDetails.Success -> _state.value = RestaurantsDetailsFragmentState.RestaurantDetails(it.data)
                is RestaurantDetails.Error -> _state.value = RestaurantsDetailsFragmentState.Error
            }
        }
//        viewModelScope.launch {
//            getRestaurantDetailsUseCase.getRestaurantDetails(id).collect {
//                when (it) {
//                    is RestaurantDetails.Success -> _state.value = RestaurantsDetailsFragmentState.RestaurantDetails(it.data)
//                    is RestaurantDetails.Error -> _state.value = RestaurantsDetailsFragmentState.Error
//                }
//            }
//        }
    }

    private suspend fun postUpdate(state: RestaurantsDetailsFragmentState) {
        withContext(SINGLE_THREAD) {
            _state.value = state
        }

    }

    companion object {
        private val SINGLE_THREAD = newSingleThreadContext("restaurantDetails")
    }
}