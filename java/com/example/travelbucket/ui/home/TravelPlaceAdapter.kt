
package com.example.travelbucket.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travelbucket.data.TravelPlace
import com.example.travelbucket.databinding.ItemTravelPlaceBinding
import com.squareup.picasso.Picasso

class TravelPlaceAdapter(
    private val travelPlaces: List<TravelPlace>,
    private val onItemClick: (TravelPlace) -> Unit
) : RecyclerView.Adapter<TravelPlaceAdapter.TravelPlaceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelPlaceViewHolder {
        val binding = ItemTravelPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TravelPlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TravelPlaceViewHolder, position: Int) {
        val travelPlace = travelPlaces[position]
        holder.bind(travelPlace)

        // Handle item click
        holder.itemView.setOnClickListener {
            onItemClick(travelPlace)
        }
    }

    override fun getItemCount() = travelPlaces.size

    inner class TravelPlaceViewHolder(private val binding: ItemTravelPlaceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(travelPlace: TravelPlace) {
            binding.textCity.text = travelPlace.city
            Picasso.get().load(travelPlace.imageUrls.firstOrNull()).into(binding.imagePlace)
        }
    }
}
