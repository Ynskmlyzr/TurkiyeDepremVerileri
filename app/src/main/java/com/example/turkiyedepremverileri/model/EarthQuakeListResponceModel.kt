package com.example.turkiyedepremverileri.model

import com.google.gson.annotations.SerializedName

class EarthQuakeListResponceModel{
    @SerializedName("result")
    var result: ArrayList<Result>? = null

    @SerializedName("status")
    var status: Boolean? = null

     data class Result(
         val _id: String,
         val created_at: Int,
         val date: String,
         val date_time: String,
         val depth: Double,
         val earthquake_id: String,
         val geojson: Geojson,
         val location_properties: LocationProperties,
         val location_tz: String,
         val mag: Double,
         val provider: String,
         val rev: String,
         val title: String
    )

    data class Geojson(
        val coordinates: List<Double>,
        val type: String
    )

    data class Coordinates(
        val coordinates: List<Double>,
        val type: String
    )

    data class LocationProperties(
        val airports: List<Airport>
    )

    data class Airport(
        val code: String,
        val coordinates: Coordinates,
        val distance: Double,
        val name: String
    )


}
