package com.dash.doorlite.presentation.restaurant.ui

import android.content.SharedPreferences
import android.text.TextUtils.isEmpty
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

    fun bind(restaurantDo: Restaurant, listener : (Restaurant) -> Unit, pref: SharedPreferences?) {
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
            var isFav = pref?.getString(restaurantDo.id, "").equals(restaurantDo.id)

            if(isFav){
                binding.fav.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else{
                binding.fav.setImageResource(R.drawable.ic_baseline_favorite_unselected_24)
            }

            distance.text = String.format(
                    binding.root.resources.getString(R.string.distance_from_consumer_format),
                    String.format("%.2f", restaurantDo.distance)
            )

            binding.fav.setOnClickListener{
                if(isFav){
                    isFav = false
                    binding.fav.setImageResource(R.drawable.ic_baseline_favorite_unselected_24)
                } else{
                    isFav = true
                    binding.fav.setImageResource(R.drawable.ic_baseline_favorite_24)
                }
                listener(restaurant!!)
            }

            restaurant = restaurantDo
        }
    }

}