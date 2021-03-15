package com.dash.doorlite.domain.restaurant.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * To show popular items
 */
@Parcelize
data class Item(
        val id: String,
        val name: String,
        val description: String,
        val imageUrl: String,
        val price: String) : Parcelable

@Parcelize
data class Restaurant(
        val id: String,
        val title: String,
        val description: String,
        val coverImage: String,
        val headerImage: String,
        val numRatings: Int,
        val distance: Double,
        val displayDeliveryFee: String,
        val popularItems: List<Item>

) : Parcelable