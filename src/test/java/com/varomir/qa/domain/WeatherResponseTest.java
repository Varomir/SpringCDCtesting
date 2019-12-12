package com.varomir.qa.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Execution(ExecutionMode.CONCURRENT)
public class WeatherResponseTest {

    @Test
    void shouldDeserializeInputJson() throws IOException {
        String jsonResponse = new String(Files.readAllBytes(
                ResourceUtils.getFile("classpath:weatherApiResponse.json").toPath()));

        WeatherResponse expectedResponse = WeatherResponse.weatherResponse().description("scattered clouds").build();
        WeatherResponse parsedResponse = new ObjectMapper().readValue(jsonResponse, WeatherResponse.class);

        assertEquals(expectedResponse, parsedResponse, "Parsed from input Json data 'description' value was not as expected");
    }
}
