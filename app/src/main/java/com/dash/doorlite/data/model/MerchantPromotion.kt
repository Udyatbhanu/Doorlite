package com.dash.doorlite.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MerchantPromotion(
    @Json(name = "category_new_store_customers_only")
    var categoryNewStoreCustomersOnly: Boolean = false,
    @Json(name = "daypart_constraints")
    var daypartConstraints: List<Any> = listOf(),
    @Json(name = "delivery_fee")
    var deliveryFee: Int? = null,
    @Json(name = "delivery_fee_monetary_fields")
    var deliveryFeeMonetaryFields: DeliveryFeeMonetaryFields = DeliveryFeeMonetaryFields(),
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "minimum_subtotal")
    var minimumSubtotal: Int? = null,
    @Json(name = "minimum_subtotal_monetary_fields")
    var minimumSubtotalMonetaryFields: MinimumSubtotalMonetaryFields = MinimumSubtotalMonetaryFields()
)