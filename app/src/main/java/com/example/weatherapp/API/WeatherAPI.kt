package com.example.weatherapp.API

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("/v1/current.json")
    // as asynchronised call therefore suspend function
    suspend fun getWeather(
        @Query("Key") apiKey : String,
        @Query("q") city : String
    ) : Response<WeatherModel>
}