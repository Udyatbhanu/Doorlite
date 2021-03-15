package com.dash.doorlite.data.model.restaurant_details


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Discount(
    @Json(name = "amount")
    var amount: Amount = Amount(),
    @Json(name = "description")
    var description: String = "",
    @Json(name = "discount_type")
    var discountType: String = "",
    @Json(name = "min_subtotal")
    var minSubtotal: MinSubtotal = MinSubtotal(),
    @Json(name = "source_type")
    var sourceType: String = "",
    @Json(name = "text")
    var text: String = ""
)