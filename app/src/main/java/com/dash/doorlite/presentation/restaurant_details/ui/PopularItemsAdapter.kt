package com.dash.doorlite.presentation.restaurant_details.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.dash.doorlite.R
import com.dash.doorlite.databinding.PopularItemsListItemBinding
import com.dash.doorlite.domain.restaurant.model.Item

class PopularItemsAdapter : ListAdapter<Item, PopularItemViewHolder>(COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularItemViewHolder {
        val binding: PopularItemsListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.popular_items_list_item,
                parent, false
        )
        return PopularItemViewHolder(
                binding
        )
    }

    override fun onBindViewHolder(holder: PopularItemViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }

    }


    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(
                    oldItem: Item,
                    newItem: Item
            ): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(
                    oldItem: Item,
                    newItem: Item
            ): Boolean =
                    oldItem.name == newItem.name
        }
    }
}