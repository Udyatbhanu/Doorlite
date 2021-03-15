package com.dash.doorlite.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PopularItem(
    @Json(name = "description")
    var description: String = "",
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "img_url")
    var imgUrl: String = "",
    @Json(name = "name")
    var name: String = "",
    @Json(name = "price")
    var price: Int = 0
)