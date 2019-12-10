package com.varomir.qa.client;

import com.varomir.qa.domain.WeatherResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@Execution(ExecutionMode.CONCURRENT)
public class WeatherClientTest {

    @Mock
    private RestTemplate restTemplate;

    private WeatherClient clientUnderTest;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        clientUnderTest = new WeatherClient(restTemplate);
    }

    @DisplayName("'WeatherClient.yesterdaysWeather()' method should return WeatherResponse object")
    @Test
    public void shouldCallYahooService() {
        given(restTemplate.getForObject(any(), eq(WeatherResponse.class)))
                .willReturn(WeatherResponse.weatherResponse().description("Kyiv, 6°C sunny").build());

        clientUnderTest.yesterdaysWeather();

        verify(restTemplate).getForObject("yahoo", WeatherResponse.class);
    }
}
