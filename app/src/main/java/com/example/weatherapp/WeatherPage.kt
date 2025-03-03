package com.example.weatherapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.API.NetworkResponse
import com.example.weatherapp.API.WeatherModel

@Composable
fun WeatherPage(viewModel: WeatherViewModel){
    
    var city by remember {
        mutableStateOf("")
    }

    val weatherResult = viewModel.weatherResult.observeAsState()

    val keyboardController = LocalSoftwareKeyboardController.current

    Column (modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
        .fillMaxWidth()
        .padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row( modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.SpaceEvenly ) {
            OutlinedTextField(value = city, modifier = Modifier.weight(1f), onValueChange = {
                city = it
            },
                label = { Text(text = "Search for any Location") }
            )
            IconButton(onClick = {
                viewModel.getData(city)
                keyboardController?.hide()
            }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }
        }
        when(val result = weatherResult.value){
            is NetworkResponse.Error -> { Text(text = result.message) }
            NetworkResponse.Loading -> {
                CircularProgressIndicator()
            }
            is NetworkResponse.Success -> {
                WeatherDetails(data = result.data)
            }
            null -> {  }
        }
    }
}

// NOW IS THE THE ENTIRE UI
@Composable
fun WeatherDetails(data : WeatherModel){
    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
            .fillMaxWidth()
            .padding(vertical = 8.dp), horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn ,
                contentDescription = "location icon",
                modifier = Modifier.size(40.dp),
            ) 
            Text(text = data.location.name, fontSize = 30.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = data.location.country , fontSize = 18.sp , color = Color.White )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "${data.current.temp_c} ° C" ,
            fontSize = 56.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        AsyncImage( modifier = Modifier.size(160.dp)
            ,model = "https:${data.current.condition.icon}".replace("64x64","128x128"), contentDescription = "condition icon")
        Text(
            text = data.current.condition.text,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card (Modifier.background(Color.White)) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .verticalScroll(
                    rememberScrollState()
                )) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    weatherkey(key = "Humidity" , value = data.current.humidity+"%" )
                    weatherkey(key = "Wind Speed", value = data.current.wind_kph + "Km/h")
                }
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    weatherkey(key = "UV" , value = data.current.uv )
                    weatherkey(key = "precipitation", value = data.current.precip_mm + "mm")
                }
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    weatherkey(key = "Local time" , value = data.location.localtime.split(" ")[1] )
                    weatherkey(key = "Local date", value = data.location.localtime.split(" ")[0] )
                }
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    weatherkey(key = "Region" , value = data.location.region )
                    weatherkey(key = "Pressure", value = data.current.pressure_mb+"Mb")
                }
            }
        }
    }
}

@Composable
fun weatherkey(key: String , value : String){
    Column (
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = value, fontSize = 24.sp, fontWeight = FontWeight.Bold , color = Color.Black)
        Text(text = key, fontWeight = FontWeight.SemiBold, color = Color.Black)
    }
}