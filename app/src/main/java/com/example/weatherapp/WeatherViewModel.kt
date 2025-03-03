package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.API.NetworkResponse
import com.example.weatherapp.API.RetrofitInstance
import com.example.weatherapp.API.WeatherModel
import com.example.weatherapp.API.constant
import kotlinx.coroutines.launch

class WeatherViewModel :ViewModel() {

    private val weatherAPI = RetrofitInstance.weatherAPI
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    // making private exposing to live data
    val weatherResult : LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    // when we click on search item it should get data from retrofit
    fun getData(city:String){
        // for loading
        _weatherResult.value = NetworkResponse.Loading

        viewModelScope.launch {
            try {
                val response = weatherAPI.getWeather(constant.apikey,city)  // this is a suspend function so we have to wrap it in a coroutine
                // this what Network response we thought about
                if(response.isSuccessful){
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Success(it)
                    }
                }
                else{
                    _weatherResult.value = NetworkResponse.Error("failed to load data")
                }
            }
            catch (e:Exception){
                _weatherResult.value = NetworkResponse.Error("failed to load data")
            }
        }

    }
}