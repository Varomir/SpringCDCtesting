package com.varomir.qa.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@Execution(ExecutionMode.CONCURRENT)
public class HelloWorldAcceptanceTest {

    @DisplayName("'/hello' endpoint should return expected response body text via HTTP protocol")
    @Test
    void shouldReturnExpectedGreetingsViaHTTP() {
        when()
                .get("http://localhost:8080/hello")
        .then()
                .statusCode(is(200))
                .body(containsString("Hello World!"));
    }
}
