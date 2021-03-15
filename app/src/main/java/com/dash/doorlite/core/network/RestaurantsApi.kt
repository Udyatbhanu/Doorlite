package com.dash.doorlite.core.network

import com.dash.doorlite.core.Endpoints
import com.dash.doorlite.data.model.RestaurantsResponse
import com.dash.doorlite.data.model.restaurant_details.RestaurantDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestaurantsApi {
    @GET(Endpoints.GET_NEARBY_RESTAURANTS)
    suspend fun getNearbyRestaurants(@Query("lat") lat: String,
                                     @Query("lng") lng: String,
                                     @Query("offset") offset: String,
                                     @Query("limit") limit: String): RestaurantsResponse

    @GET(Endpoints.GET_RESTAURANT_DETAILS)
    suspend fun getRestaurantDetails(@Path("id") id: String): RestaurantDetailsResponse

}