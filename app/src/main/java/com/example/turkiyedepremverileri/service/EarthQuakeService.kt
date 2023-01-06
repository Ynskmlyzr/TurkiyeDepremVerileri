package com.example.turkiyedepremverileri.service

import com.example.turkiyedepremverileri.model.EarthQuakeListResponceModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class EarthQuakeService{

    companion object{
        const val BASE_URL = "https://api.orhanaydogdu.com.tr/deprem/"
    }

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(EarthQuakeAPI::class.java)


fun getEarthQuakeList() : Single<EarthQuakeListResponceModel> {
    return api.getEarthQuakeList()
}
}