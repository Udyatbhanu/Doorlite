package com.dash.doorlite.data.model.restaurant_details


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OpenHour(
    @Json(name = "hour")
    var hour: Int = 0,
    @Json(name = "minute")
    var minute: Int = 0
)