package com.sammuiga.emergram.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sammuiga.emergram.R
import com.sammuiga.emergram.SavedPlaceModel
import com.sammuiga.emergram.databinding.SavedItemLayoutBinding
import com.sammuiga.emergram.interfaces.SaveLocationInterface

class SavedPlaceAdapter(val listener: SaveLocationInterface) :
    ListAdapter<SavedPlaceModel, SavedPlaceAdapter.ViewHolder>(PlaceComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<SavedItemLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.saved_item_layout,
            parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = getItem(position)
        holder.binding.savedPlaceModel = place
        holder.binding.listener = listener
    }

    class ViewHolder(val binding: SavedItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    class PlaceComparator : DiffUtil.ItemCallback<SavedPlaceModel>() {
        override fun areItemsTheSame(oldItem: SavedPlaceModel, newItem: SavedPlaceModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SavedPlaceModel,
            newItem: SavedPlaceModel
        ): Boolean {
            return oldItem.placeId == newItem.placeId
        }
    }
}