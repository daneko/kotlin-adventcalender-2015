package com.github.daneko.android.kotlin

import retrofit.http.GET
import retrofit.http.Query
import rx.Observable

/**

 */
interface WeatherApi {

    @GET("/data/2.5/weather")
    fun get(@Query("q") q: String, @Query("appid") apiKey: String): Observable<WeatherResponse>

    companion object {
        val OPEN_WEATHER_MAP_API = "http://api.openweathermap.org"
    }
}
