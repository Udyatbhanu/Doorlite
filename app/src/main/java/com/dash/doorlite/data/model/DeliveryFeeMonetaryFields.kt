package com.dash.doorlite.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeliveryFeeMonetaryFields(
    @Json(name = "currency")
    var currency: String = "",
    @Json(name = "decimal_places")
    var decimalPlaces: Int = 0,
    @Json(name = "display_string")
    var displayString: String = "",
    @Json(name = "unit_amount")
    var unitAmount: Int? = null
)