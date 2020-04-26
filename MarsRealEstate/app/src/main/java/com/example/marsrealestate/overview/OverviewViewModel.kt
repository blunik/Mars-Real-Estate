package com.example.marsrealestate.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call
import com.example.marsrealestate.network.MarsApi
import com.example.marsrealestate.network.MarsProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class OverviewViewModel: ViewModel() {

    private val _status= MutableLiveData<String>()
    val status: LiveData<String>
    get() = _status

    private val _properties = MutableLiveData<MarsProperty>()
    val properties: LiveData<MarsProperty>
    get() = _properties

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init{
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties() {
        coroutineScope.launch {

           var getPropertiesDeferred=  MarsApi.retrofitService.getProperties()
            try {
                var listResult = getPropertiesDeferred.await()
                if (listResult.size > 0){
                    _properties.value = listResult[0]
                }
            } catch(e: Exception){
                    _status.value = "Failure: ${e.message}"
                }

            }
        }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
