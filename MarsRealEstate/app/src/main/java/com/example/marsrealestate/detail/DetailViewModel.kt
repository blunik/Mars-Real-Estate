package com.example.marsrealestate.detail

import android.app.Application
import android.net.wifi.WifiConfiguration.AuthAlgorithm.strings
import androidx.lifecycle.*
import com.example.marsrealestate.R
import com.example.marsrealestate.detail.DetailFragment
import com.example.marsrealestate.network.MarsProperty

class DetailViewModel(marsProperty: MarsProperty, app: Application) : AndroidViewModel(app) {
    private val _selectedProperty = MutableLiveData<MarsProperty>()
    val selectedProperty: LiveData<MarsProperty>
    get() = _selectedProperty

    init {
        _selectedProperty.value = marsProperty
    }

    val displayPropertyPrice = Transformations.map(selectedProperty) {
        app.applicationContext.getString(
            when (it.isRental) {
                true -> R.string.display_price_monthly_rental
                false -> R.string.display_price
            }, it.price)
    }

    val displayPropertyType = Transformations.map(selectedProperty){
        app.applicationContext.getString(R.string.display_type,
        app.applicationContext.getString(
            when(it.isRental) {
                true -> R.string.type_rent
                false -> R.string.type_sale
            }
        ))
    }


}