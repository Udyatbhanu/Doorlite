package com.dash.doorlite.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Status(
    @Json(name = "asap_available")
    var asapAvailable: Boolean = false,
    @Json(name = "asap_minutes_range")
    var asapMinutesRange: List<Int> = listOf(),
    @Json(name = "pickup_available")
    var pickupAvailable: Boolean = false,
    @Json(name = "scheduled_available")
    var scheduledAvailable: Boolean = false,
    @Json(name = "unavailable_reason")
    var unavailableReason: Any? = null
)