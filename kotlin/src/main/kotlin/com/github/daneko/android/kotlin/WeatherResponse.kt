package com.github.daneko.android.kotlin

data class WeatherResponse(val weather: List<WeatherResponse.Weather>) {
    data class Weather(val main: String)
}
