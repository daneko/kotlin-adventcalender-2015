package com.github.daneko.android.plain;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.*;


/**
 *
 */
public class WeatherServiceTest {

    private MockWebServer mockWebServer;

    @Before
    public void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @After
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void とりあえず正常系だけ() throws IOException {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseSample()));

        final String s = mockWebServer.url("/").toString();
        final String expected = WeatherService.currentTokyoWeather(s)
                .toBlocking().first().getMain();
        assertThat(expected).isEqualTo("Drizzle");
    }


    private String responseSample() throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(getClass().getClassLoader().getResourceAsStream("response_sample.json"), "UTF-8"));
        for (int c = br.read(); c != -1; c = br.read()) {
            sb.append((char) c);
        }
        return sb.toString();
    }

}
