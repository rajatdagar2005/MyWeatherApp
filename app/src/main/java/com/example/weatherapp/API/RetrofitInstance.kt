package com.example.weatherapp.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val baseUrl = "https://api.weatherapi.com"

    private fun getInstance() : Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
    }
    val weatherAPI :WeatherAPI = getInstance().create(WeatherAPI :: class.java)
}