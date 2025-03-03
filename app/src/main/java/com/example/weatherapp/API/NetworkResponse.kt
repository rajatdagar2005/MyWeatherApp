package com.example.weatherapp.API


//  T refers to weatherModel
sealed class NetworkResponse <out T> {   // <out T> so that we can wrap it with anything
    data class Success<out T>(val data : T) : NetworkResponse<T>()
    data class Error(val message : String) : NetworkResponse<Nothing>()
    object Loading : NetworkResponse<Nothing>()
}