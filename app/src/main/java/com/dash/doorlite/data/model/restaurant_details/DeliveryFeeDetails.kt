package com.dash.doorlite.data.model.restaurant_details


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeliveryFeeDetails(
    @Json(name = "discount")
    var discount: Discount = Discount(),
    @Json(name = "final_fee")
    var finalFee: FinalFee = FinalFee(),
    @Json(name = "original_fee")
    var originalFee: OriginalFee = OriginalFee(),
    @Json(name = "surge_fee")
    var surgeFee: SurgeFee = SurgeFee()
)