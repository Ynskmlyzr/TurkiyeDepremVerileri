package com.example.turkiyedepremverileri.service

import com.example.turkiyedepremverileri.model.EarthQuakeListResponceModel
import io.reactivex.Single
import retrofit2.http.GET

interface EarthQuakeAPI {
    companion object{
        const val LIMIT = "live.php?limit=100"
    }

    @GET(LIMIT)
    fun getEarthQuakeList() : Single<EarthQuakeListResponceModel>
}