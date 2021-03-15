package com.dash.doorlite.domain.restaurant


import com.dash.doorlite.core.presentation.viewmodel.Location
import com.dash.doorlite.domain.restaurant.model.Restaurants
import kotlinx.coroutines.flow.Flow

interface GetNearbyRestaurantsUseCase {
    suspend fun getNearbyRestaurantsStream(location : Location): Flow<Restaurants>
    suspend fun requestMore(location : Location?)
}