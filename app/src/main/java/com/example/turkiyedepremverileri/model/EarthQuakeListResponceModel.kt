package com.example.turkiyedepremverileri.model

import com.google.gson.annotations.SerializedName

class EarthQuakeListResponceModel{
    @SerializedName("result")
    var result: ArrayList<Result>? = null

    @SerializedName("status")
    var status: Boolean? = null

    data class Result(
        @SerializedName("coordinates")
        var coordinates: List<Double?>? = null,
        @SerializedName("date")
        var date: String? = null,
        @SerializedName("depth")
        var depth: Double? = null,
        @SerializedName("lat")
        var lat: Double? = null,
        @SerializedName("lng")
        var lng: Double? = null,
        @SerializedName("lokasyon")
        var lokasyon: String? = null,
        @SerializedName("mag")
        var mag: Double? = null,
        @SerializedName("title")
        var title: String? = null
    )
}
