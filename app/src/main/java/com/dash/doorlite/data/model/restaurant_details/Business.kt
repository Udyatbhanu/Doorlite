package com.dash.doorlite.data.model.restaurant_details


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Business(
    @Json(name = "business_vertical")
    var businessVertical: Any? = null,
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "name")
    var name: String = ""
)