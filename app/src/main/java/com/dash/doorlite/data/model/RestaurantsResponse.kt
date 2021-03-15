package com.dash.doorlite.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RestaurantsResponse(
    @Json(name = "is_first_time_user")
    var isFirstTimeUser: Boolean? = false,
    @Json(name = "next_offset")
    var nextOffset: Int = 0,
    @Json(name = "num_results")
    var numResults: Int? = 0,
    @Json(name = "show_list_as_pickup")
    var showListAsPickup: Boolean? = false,
    @Json(name = "sort_order")
    var sortOrder: String? = "",
    @Json(name = "stores")
    var stores: List<Store> = listOf()
)