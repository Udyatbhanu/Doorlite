package com.dash.doorlite.presentation.restaurant.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.dash.doorlite.R
import com.dash.doorlite.databinding.RestaurantsListItemBinding
import com.dash.doorlite.domain.restaurant.model.Restaurant

class RestaurantsAdapter :
    ListAdapter<Restaurant, RestaurantsViewHolder>(COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantsViewHolder {
        val binding: RestaurantsListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.restaurants_list_item,
            parent, false
        )
        return RestaurantsViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: RestaurantsViewHolder, position: Int) {
        val restaurant = getItem(position)
        if(restaurant != null){
            holder.bind(restaurant)
        }

    }


    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Restaurant>() {
            override fun areItemsTheSame(
                    oldItem: Restaurant,
                    newItem: Restaurant
            ): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(
                oldItem: Restaurant,
                newItem: Restaurant
            ): Boolean =
                oldItem == newItem
        }
    }
}