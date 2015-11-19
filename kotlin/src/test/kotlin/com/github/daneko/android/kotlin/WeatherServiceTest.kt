package com.github.daneko.android.kotlin

import com.squareup.okhttp.mockwebserver.*
import org.assertj.core.api.Assertions.*
import org.junit.*


/**

 */
class WeatherServiceTest {

    val _mockWebServer = MockWebServer()

    @Rule public fun mockWebServer() = _mockWebServer

    @Test
    fun とりあえず正常系だけ() {
        mockWebServer().enqueue(MockResponse().setResponseCode(200).setBody(responseSample()))
        val s = mockWebServer().url("/").toString()
        val expected = WeatherService.currentTokyoWeather(s).toBlocking().first().main
        assertThat(expected).isEqualTo("Drizzle")
    }

    private fun responseSample(): String {
        return """
{
  "coord": {
    "lon": -0.13,
    "lat": 51.51
  },
  "weather": [
    {
      "id": 310,
      "main": "Drizzle",
      "description": "light intensity drizzle rain",
      "icon": "09n"
    }
  ],
  "base": "stations",
  "main": {
    "temp": 286.26,
    "pressure": 1007,
    "humidity": 87,
    "temp_min": 285.15,
    "temp_max": 287.15
  },
  "visibility": 10000,
  "wind": {
    "speed": 9.3,
    "deg": 210,
    "gust": 14.4
  },
  "clouds": {
    "all": 75
  },
  "dt": 1447640400,
  "sys": {
    "type": 1,
    "id": 5078,
    "message": 0.0348,
    "country": "GB",
    "sunrise": 1447658385,
    "sunset": 1447690208
  },
  "id": 2643743,
  "name": "London",
  "cod": 200
}

        """
    }

}


