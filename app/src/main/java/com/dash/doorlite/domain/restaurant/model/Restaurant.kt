package com.dash.doorlite.domain.restaurant.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Restaurant(
        val id: String,
        val title: String,
        val description: String,
        val coverImage: String,
        val headerImage: String,
        val numRatings: Int,
        val distance: Double,
        val displayDeliveryFee : String

) : Parcelable