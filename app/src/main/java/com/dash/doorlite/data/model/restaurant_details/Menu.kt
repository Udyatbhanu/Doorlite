package com.dash.doorlite.data.model.restaurant_details


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Menu(
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "is_business_enabled")
    var isBusinessEnabled: Any? = null,
    @Json(name = "is_catering")
    var isCatering: Boolean = false,
    @Json(name = "menu_version")
    var menuVersion: Int = 0,
    @Json(name = "name")
    var name: String = "",
    @Json(name = "open_hours")
    var openHours: List<List<OpenHour>> = listOf(),
    @Json(name = "status")
    var status: String = "",
    @Json(name = "status_type")
    var statusType: String = "",
    @Json(name = "subtitle")
    var subtitle: String = ""
)