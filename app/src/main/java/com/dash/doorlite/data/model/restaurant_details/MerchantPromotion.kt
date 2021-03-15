package com.dash.doorlite.data.model.restaurant_details


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MerchantPromotion(
    @Json(name = "category_id")
    var categoryId: Int = 0,
    @Json(name = "delivery_fee")
    var deliveryFee: Int? = null,
    @Json(name = "description")
    var description: String? = null,
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "minimum_order_cart_subtotal")
    var minimumOrderCartSubtotal: Int? = null,
    @Json(name = "new_store_customers_only")
    var newStoreCustomersOnly: Boolean = false,
    @Json(name = "sort_order")
    var sortOrder: Int = 0,
    @Json(name = "title")
    var title: String = ""
)