package com.dash.doorlite.data.model.restaurant_details


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MinSubtotal(
    @Json(name = "currency")
    var currency: String = "",
    @Json(name = "display_string")
    var displayString: String = "",
    @Json(name = "unit_amount")
    var unitAmount: Int = 0
)