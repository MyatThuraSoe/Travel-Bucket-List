package com.example.travelbucket.data


import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "travel_place_table")
data class TravelPlace(
    @PrimaryKey
    val _id: String,
    val city: String,
    val imageUrls: List<String>, // Requires TypeConverter
    val description: String,
    val estimatedCost: Int,
    val bestSeasonToTravel: String
)
