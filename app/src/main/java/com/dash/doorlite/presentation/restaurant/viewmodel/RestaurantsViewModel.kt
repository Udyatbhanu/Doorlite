package com.dash.doorlite.presentation.restaurant.viewmodel

import androidx.lifecycle.*
import com.dash.doorlite.core.AppManager
import com.dash.doorlite.core.IModel
import com.dash.doorlite.core.presentation.viewmodel.Location
import com.dash.doorlite.domain.restaurant.GetNearbyRestaurantsUseCase
import com.dash.doorlite.domain.restaurant.model.Restaurant
import com.dash.doorlite.domain.restaurant.model.Restaurants
import com.dash.doorlite.presentation.restaurant.ui.RestaurantsFragmentIntent
import com.dash.doorlite.presentation.restaurant.ui.RestaurantsFragmentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantsViewModel @Inject constructor(private val getNearbyRestaurantsUseCase: GetNearbyRestaurantsUseCase, private val appManager: AppManager) :
        ViewModel(), IModel<RestaurantsFragmentState, RestaurantsFragmentIntent> {
    private val locationLiveData = MutableLiveData<Location>()


    //The intent
    override val intents: Channel<RestaurantsFragmentIntent> = Channel(Channel.UNLIMITED)
    val state: LiveData<Restaurants> = locationLiveData.switchMap { location ->
        liveData {
            val restaurants = getNearbyRestaurantsUseCase.getNearbyRestaurantsStream(location)
                    .asLiveData(Dispatchers.Main)
            emitSource(restaurants)
        }
    }


    //init
    init {
        handlerIntent()
    }

    private fun handlerIntent() {
        // Lambda function to launchViewModelScope on a single thread dispatcher
        viewModelScope.launch {
            intents.consumeAsFlow().collect {
                when (it) {
                    else -> RestaurantsFragmentState.Idle
                }

            }

        }
    }


    fun selectFav(restaurant : Restaurant){
        //save restaurant
        restaurant
    }

    fun getEateries(location: Location) {
        locationLiveData.postValue(location)
    }

    fun listScrolled() {
        viewModelScope.launch {
            val locationQuery = locationLiveData.value
            getNearbyRestaurantsUseCase.requestMore(locationQuery)
        }
    }

}

