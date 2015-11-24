package com.github.daneko.android.kotlin

import proguard.annotation.KeepClassMemberNames

@KeepClassMemberNames
data class WeatherResponse(val weather: List<WeatherResponse.Weather>) {
    @KeepClassMemberNames
    data class Weather(val main: String)
}
