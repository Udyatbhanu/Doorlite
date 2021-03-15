package com.dash.doorlite.data.repository

import com.dash.doorlite.core.BaseRepository
import com.dash.doorlite.core.network.RestaurantsApi
import com.dash.doorlite.core.service.ResultWrapper
import com.dash.doorlite.data.model.RestaurantsResponse
import com.dash.doorlite.data.model.restaurant_details.RestaurantDetailsResponse
import javax.inject.Inject

class RestaurantsRepository @Inject constructor(private val api: RestaurantsApi) : BaseRepository() {

    suspend fun getNearbyRestaurants(lat: String, lng: String, offset: String, limit: String): ResultWrapper<RestaurantsResponse> =
            invoke { api.getNearbyRestaurants(lat, lng, offset, limit) }


    suspend fun getRestaurantDetails(id: String): ResultWrapper<RestaurantDetailsResponse> =
        invoke { api.getRestaurantDetails(id) }
}