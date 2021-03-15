package com.dash.doorlite.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Store(
    @Json(name = "average_rating")
    var averageRating: Double = 0.0,
    @Json(name = "business_id")
    var businessId: Int = 0,
    @Json(name = "cover_img_url")
    var coverImgUrl: String = "",
    @Json(name = "delivery_fee")
    var deliveryFee: Int = 0,
    @Json(name = "delivery_fee_monetary_fields")
    var deliveryFeeMonetaryFields: DeliveryFeeMonetaryFields = DeliveryFeeMonetaryFields(),
    @Json(name = "description")
    var description: String = "",
    @Json(name = "display_delivery_fee")
    var displayDeliveryFee: String = "",
    @Json(name = "distance_from_consumer")
    var distanceFromConsumer: Double = 0.0,
    @Json(name = "extra_sos_delivery_fee")
    var extraSosDeliveryFee: Int = 0,
    @Json(name = "extra_sos_delivery_fee_monetary_fields")
    var extraSosDeliveryFeeMonetaryFields: ExtraSosDeliveryFeeMonetaryFields = ExtraSosDeliveryFeeMonetaryFields(),
    @Json(name = "header_img_url")
    var headerImgUrl: String = "",
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "is_consumer_subscription_eligible")
    var isConsumerSubscriptionEligible: Boolean = false,
    @Json(name = "is_newly_added")
    var isNewlyAdded: Boolean = false,
    @Json(name = "location")
    var location: Location = Location(),
    @Json(name = "menus")
    var menus: List<Menu> = listOf(),
    @Json(name = "merchant_promotions")
    var merchantPromotions: List<MerchantPromotion> = listOf(),
    @Json(name = "name")
    var name: String = "",
    @Json(name = "next_close_time")
    var nextCloseTime: String = "",
    @Json(name = "next_open_time")
    var nextOpenTime: String = "",
    @Json(name = "num_ratings")
    var numRatings: Int = 0,
    @Json(name = "price_range")
    var priceRange: Int = 0,
    @Json(name = "promotion_delivery_fee")
    var promotionDeliveryFee: Int = 0,
    @Json(name = "service_rate")
    var serviceRate: Any? = null,
    @Json(name = "status")
    var status: Status = Status(),
    @Json(name = "url")
    var url: String = ""
)