package com.varomir.qa.rest;

import com.github.javafaker.Name;
import com.github.jenspiegsa.wiremockextension.Managed;
import com.github.jenspiegsa.wiremockextension.WireMockExtension;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.varomir.qa.commons.utils.WithFaker;
import com.varomir.qa.domain.Person;
import com.varomir.qa.repository.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;

import static com.github.jenspiegsa.wiremockextension.ManagedWireMockServer.with;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;


@DisplayName("Acceptance tests")
@ExtendWith({SpringExtension.class, WireMockExtension.class})
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Execution(ExecutionMode.SAME_THREAD)
public class HelloWorldAcceptanceTest implements WithFaker {

    @Autowired
    PersonRepository personRepository;

    @LocalServerPort
    private int port;
    private String firstName, lastName;

    @Managed
    public WireMockServer s1 = with(wireMockConfig().port(port));

    @DisplayName("'/hello' endpoint should return expected response body text via HTTP protocol")
    @Test
    void shouldReturnExpectedGreetingsViaHTTP() {
        when()
                .get("http://localhost:" + port + "/hello")
        .then()
                .statusCode(is(200))
                .body(containsString("Hello World!"));
    }

    @DisplayName("'/hello/${lastName}' endpoint should return expected greeting for the person")
    @Test
    public void shouldReturnExpectedGreetingsForPerson() {
        Name fakePersonName = getFakePersonName();
        firstName = fakePersonName.firstName();
        lastName = fakePersonName.lastName();
        Person fakePerson = new Person(firstName, lastName);
        personRepository.save(fakePerson);

        when()
                .get(String.format("http://localhost:%d/hello/%s", port, lastName))
        .then()
                .statusCode(is(200))
                .body(containsString(String.format("Hello %s %s!", firstName, lastName)));
    }

    @DisplayName("'/yesterdaysWeather' endpoint should return weather data in JSON format")
    @Test
    void shouldReturnYesterdaysWeather() throws IOException {
        s1.stubFor(get("/yesterdaysWeather")
                .willReturn(aResponse()
                        .withBody(Files.readAllBytes(ResourceUtils.getFile("classpath:weatherApiResponse.json").toPath()))
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)));

        when()
                .get(String.format("http://localhost:%s/yesterdaysWeather", s1.port()))
                .then()
                .statusCode(is(200))
                .body(containsString("scattered clouds"));
    }
}
