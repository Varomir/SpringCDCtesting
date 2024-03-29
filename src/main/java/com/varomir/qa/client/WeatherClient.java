package com.varomir.qa.client;

import com.varomir.qa.domain.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherClient {

    private final RestTemplate restTemplate;
    private final String weatherServiceUrl;

    @Autowired
    public WeatherClient(RestTemplate restTemplate, @Value("${weather.url}") final String weatherServiceUrl) {
        this.restTemplate = restTemplate;
        this.weatherServiceUrl = weatherServiceUrl;
    }

    public WeatherResponse yesterdaysWeather() {
        System.out.println("weatherServiceUrl = " + weatherServiceUrl);
        return restTemplate.getForObject(weatherServiceUrl, WeatherResponse.class);
    }
}