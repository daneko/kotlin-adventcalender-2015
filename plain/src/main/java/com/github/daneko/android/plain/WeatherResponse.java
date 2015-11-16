package com.github.daneko.android.plain;

import java.util.List;

/**
 *
 */
public class WeatherResponse {
    final List<Weather> weather;

    public List<Weather> getWeather() {
        return weather;
    }

    public WeatherResponse(final List<Weather> weather) {
        this.weather = weather;
    }

    public static class Weather {
        final String main;

        public Weather(final String main) {
            this.main = main;
        }

        public String getMain() {
            return main;
        }
    }
}
