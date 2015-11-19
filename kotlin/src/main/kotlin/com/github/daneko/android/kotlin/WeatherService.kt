package com.github.daneko.android.kotlin

import retrofit.MoshiConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory
import rx.Observable

/**
 *
 */
object WeatherService {

    /**
     * @param baseUrl for testing
     */
    fun currentTokyoWeather(baseUrl: String = WeatherApi.OPEN_WEATHER_MAP_API): Observable<WeatherResponse.Weather> {

        val weatherApi = Retrofit.Builder().
                baseUrl(baseUrl).
                addConverterFactory(MoshiConverterFactory.create()).
                addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                build().
                create(WeatherApi::class.java)

        return weatherApi.get("Tokyo", BuildConfig.OPEN_WEATHER_MAP_APIKEY).
                flatMapIterable { it.weather }
    }

}
