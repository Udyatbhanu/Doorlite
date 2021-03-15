package com.dash.doorlite.data.model.restaurant_details


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Address(
    @Json(name = "city")
    var city: String = "",
    @Json(name = "country")
    var country: String = "",
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "lat")
    var lat: Double = 0.0,
    @Json(name = "lng")
    var lng: Double = 0.0,
    @Json(name = "printable_address")
    var printableAddress: String = "",
    @Json(name = "shortname")
    var shortname: String = "",
    @Json(name = "state")
    var state: String = "",
    @Json(name = "street")
    var street: String = "",
    @Json(name = "subpremise")
    var subpremise: String = "",
    @Json(name = "zip_code")
    var zipCode: String = ""
)