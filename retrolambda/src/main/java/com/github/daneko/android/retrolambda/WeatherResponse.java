package com.github.daneko.android.retrolambda;

import java.util.List;

import lombok.Value;

@Value
public class WeatherResponse {
    List<Weather> weather;

    @Value
    public static class Weather {
        final String main;
    }
}
