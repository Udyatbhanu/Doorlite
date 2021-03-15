package com.dash.doorlite.domain.restaurant_details

import com.dash.doorlite.domain.restaurant_details.model.RestaurantDetails
import kotlinx.coroutines.flow.Flow

interface GetRestaurantDetailsUseCase {
    suspend fun getRestaurantDetails(id: String): Flow<RestaurantDetails>
}