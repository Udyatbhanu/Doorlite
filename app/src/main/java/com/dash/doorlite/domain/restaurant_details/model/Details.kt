package com.dash.doorlite.domain.restaurant_details.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Details(val id: String, val deliveryFee: String, val deliveryTime: String, val averageRating: String, val numberOfRatings: String, val tags: List<String>) : Parcelable