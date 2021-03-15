package com.dash.doorlite.presentation.restaurant_details.ui

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dash.doorlite.R
import com.dash.doorlite.databinding.PopularItemsListItemBinding
import com.dash.doorlite.domain.restaurant.model.Item

class PopularItemViewHolder(val binding: PopularItemsListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(itemDo: Item) {
        with(binding) {

            //Set the image for the popular item
            Glide.with(root.context)
                    .asBitmap()
                    .load(itemDo.imageUrl)
                    .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(16))
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.image)

            item = itemDo
        }

    }
}