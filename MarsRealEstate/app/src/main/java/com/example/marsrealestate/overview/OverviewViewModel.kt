package com.example.marsrealestate.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call
import com.example.marsrealestate.network.MarsApi

class OverviewViewModel: ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
    get() = _response

    init{
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties() {
        MarsApi.retrofitService.getProperties().enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
               _response.value = response.body()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

        })
        _response.value = "Set the Mars API Response here!"
    }
}