package com.github.daneko.android.retrolambda;

import java.util.List;

import lombok.Value;

import proguard.annotation.KeepClassMemberNames;

@KeepClassMemberNames
@Value
public class WeatherResponse {
    List<Weather> weather;

    @KeepClassMemberNames
    @Value
    public static class Weather {
        final String main;
    }
}
