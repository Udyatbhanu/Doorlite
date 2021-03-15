package com.dash.doorlite.domain.restaurant.model

import java.lang.Exception

sealed class Restaurants {
    data class Success(val data: List<Restaurant>) : Restaurants()
    data class Error(val error: Exception) : Restaurants()
}
