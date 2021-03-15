package com.dash.doorlite.presentation.restaurant.ui

import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dash.doorlite.R
import com.dash.doorlite.databinding.RestaurantsListItemBinding
import com.dash.doorlite.domain.restaurant.model.Restaurant

class RestaurantsViewHolder(private val binding: RestaurantsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    fun bind(restaurantDo: Restaurant) {
        with(binding) {
            Glide.with(root.context)
                    .asBitmap()
                    .load(restaurantDo.headerImage)
                    .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(16))
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder))
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(binding.coverImage)

            var bundle = bundleOf("restaurant" to restaurantDo)
            restaurantLayout.setOnClickListener {
                it.findNavController().navigate(R.id.restaurantDetailsFragment, bundle)
            }

            distance.text = String.format(
                    binding.root.resources.getString(R.string.distance_from_consumer_format),
                    String.format("%.2f", restaurantDo.distance)
            )
            restaurant = restaurantDo
        }
    }

}