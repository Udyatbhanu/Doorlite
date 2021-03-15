package com.dash.doorlite.domain.restaurant_details.model

import com.dash.doorlite.domain.restaurant.model.Restaurant
import java.lang.Exception

sealed class RestaurantDetails {
    data class Success(val data: Details) : RestaurantDetails()
    data class Error(val error: Exception) : RestaurantDetails()
}