package com.example.turkiyedepremverileri.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.example.turkiyedepremverileri.model.EarthQuakeListResponceModel
import com.example.turkiyedepremverileri.service.EarthQuakeService
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class EarthQuakeListViewModel (application: Application) : BaseViewModel(application) {


    val earthQuakeService = EarthQuakeService()
    var earthQuakeListLiveData: MutableLiveData<EarthQuakeListResponceModel> = MutableLiveData()
    var errorLiveData: MutableLiveData<String> = MutableLiveData()
    var listLoading = MutableLiveData<Boolean>()

    fun getEarthQuakeList(){
        listLoading.postValue(true)
        earthQuakeService.getEarthQuakeList()
            .subscribeOn(Schedulers.newThread())
            .subscribeWith(object : DisposableSingleObserver<EarthQuakeListResponceModel>() {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onSuccess(earthQuakeList: EarthQuakeListResponceModel) {
                    earthQuakeListLiveData.postValue(earthQuakeList)
                    listLoading.postValue(false)
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    errorLiveData.postValue(e.message)
                    listLoading.postValue(false)
                }
            })
    }
}