package com.varomir.qa.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

    private List<Weather> weather;

    public WeatherResponse() {}

    public WeatherResponse(List<Weather> weather) {
        this.weather = weather;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getDescription() {
        return weather.stream()
                .map(Weather::getDescription)
                .collect(Collectors.joining(", "));
    }

    public static WeatherResponseBuilder weatherResponse() {
        return new WeatherResponseBuilder();
    }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "weather=" + weather +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherResponse that = (WeatherResponse) o;

        return weather.equals(that.weather);
    }

    @Override
    public int hashCode() {
        return weather.hashCode();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Weather {
        private String description;

        public String getDescription() {
            return description;
        }

        public Weather() {}

        public Weather(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "Weather{" +
                    "description='" + description + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Weather weather = (Weather) o;
            return description.equals(weather.description);
        }

        @Override
        public int hashCode() {
            return description.hashCode();
        }
    }

    public static class WeatherResponseBuilder {
        private String description;

        public WeatherResponseBuilder description(String description) {
            this.description = description;
            return this;
        }

        public WeatherResponse build() {
            return new WeatherResponse(Collections.singletonList(new Weather(description)));
        }
    }
}
