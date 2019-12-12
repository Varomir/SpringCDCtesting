package com.varomir.qa.client;

import com.varomir.qa.domain.WeatherResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

@Execution(ExecutionMode.CONCURRENT)
public class WeatherClientTest {

    @Mock
    private RestTemplate restTemplate;

    private WeatherClient clientUnderTest;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        clientUnderTest = new WeatherClient(restTemplate, "http://localhost:8099");
    }

    @DisplayName("'WeatherClient.yesterdaysWeather()' method should return WeatherResponse object")
    @Test
    public void shouldCallWeatherService() {
        WeatherResponse expectedResponse = WeatherResponse.weatherResponse().description("Kyiv, 6°C sunny").build();
        given(restTemplate.getForObject("http://localhost:8099", WeatherResponse.class))
                .willReturn(expectedResponse);

        WeatherResponse actualResponse = clientUnderTest.yesterdaysWeather();

        assertEquals(expectedResponse, actualResponse, "'WeatherResponse.yesterdaysWeather()' method return incorrect data!");
    }
}
