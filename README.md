# 🌦️ Weather App - Android (Kotlin, Jetpack Compose)

## 📌 Overview
This **Weather App** is built using **Kotlin and Jetpack Compose**, following the **MVVM architecture**. It integrates with a weather API using **Retrofit 2**, handles errors using **try-catch and throw**, and displays weather data using **Jetpack Compose UI components**. The app efficiently loads images using **Coil** and updates UI reactively with **LiveData**.

## 🛠️ Features
- 📌 **MVVM Architecture** for structured code.
- 🌐 **Retrofit 2** for API integration.
- 🔄 **LiveData** for real-time UI updates.
- 🔒 **Sealed Classes** for API response handling.
- ⚡ **Suspend Functions** for efficient network calls.
- 🎨 **Jetpack Compose UI** with **Card** components.
- 🖼️ **Coil** for image loading.
- 🚨 **Try-Catch & Throw** for error handling.

## 📦 Dependencies
Add the following dependencies in your `build.gradle`:
```gradle
val retrofitVersion = "2.11.0"

implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")

implementation("androidx.compose.runtime:runtime-livedata:1.7.8")
implementation("io.coil-kt:coil-compose:2.6.0")

git clone https://github.com/your-username/weather-app.git
cd weather-app


<uses-permission android:name="android.permission.INTERNET" />

## 🛠️ Technologies Used
Kotlin with Jetpack Compose
Retrofit 2 for API calls
LiveData for state management
Sealed classes for API response handling
Coil for image loading
