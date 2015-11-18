package com.github.daneko.android.retrolambda;

import rx.Observable;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 *
 */
public interface WeatherApi {

    String OPEN_WEATHER_MAP_API = "http://api.openweathermap.org";

    @GET("/data/2.5/weather")
    Observable<WeatherResponse> get(@Query("q") String q, @Query("appid") String apiKey);
}
