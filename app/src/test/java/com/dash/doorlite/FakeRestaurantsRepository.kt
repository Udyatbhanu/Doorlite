package com.dash.doorlite

import com.dash.doorlite.core.service.ResultWrapper
import com.dash.doorlite.data.model.RestaurantsResponse
import com.dash.doorlite.data.model.restaurant_details.RestaurantDetailsResponse

class FakeRestaurantsRepository {

    suspend fun getNearbyRestaurants(): ResultWrapper<RestaurantsResponse> {
        return ResultWrapper.Success(
            RestaurantsResponse(
                isFirstTimeUser = false,
                nextOffset = 2,
                numResults = 400,
                showListAsPickup = false,
                stores = emptyList()
            )
        )
    }

    suspend fun getRestaurantDetails(): ResultWrapper<RestaurantDetailsResponse> {
        return ResultWrapper.Success(
            RestaurantDetailsResponse(
                averageRating = 4.4, deliveryFee = 0, name = "Pam's Deli"
            )
        )
    }
}