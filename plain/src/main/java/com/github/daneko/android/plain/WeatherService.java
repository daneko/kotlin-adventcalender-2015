package com.github.daneko.android.plain;

import android.support.annotation.VisibleForTesting;

import rx.Observable;
import rx.functions.Func1;

import retrofit.MoshiConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 *
 */
public class WeatherService {

    public static Observable<WeatherResponse.Weather> currentTokyoWeather() {
        return currentTokyoWeather(WeatherApi.OPEN_WEATHER_MAP_API);
    }

    @VisibleForTesting
    static Observable<WeatherResponse.Weather> currentTokyoWeather(final String baseUrl) {

        final WeatherApi weatherApi = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(WeatherApi.class);

        return weatherApi.get("Tokyo", BuildConfig.OPEN_WEATHER_MAP_APIKEY)
                .flatMapIterable(new Func1<WeatherResponse, Iterable<? extends WeatherResponse.Weather>>() {
                    @Override
                    public Iterable<? extends WeatherResponse.Weather> call(WeatherResponse weatherResponse) {
                        return weatherResponse.getWeather();
                    }
                });
    }

}
