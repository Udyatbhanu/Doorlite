package com.dash.doorlite.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Menu(
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "is_catering")
    var isCatering: Boolean = false,
    @Json(name = "name")
    var name: String = "",
    @Json(name = "popular_items")
    var popularItems: List<PopularItem> = listOf(),
    @Json(name = "subtitle")
    var subtitle: String = ""
)