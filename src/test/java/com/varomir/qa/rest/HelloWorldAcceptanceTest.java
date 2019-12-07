package com.varomir.qa.rest;

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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Execution(ExecutionMode.CONCURRENT)
public class HelloWorldAcceptanceTest {

    @Autowired
    PersonRepository personRepository;

    @LocalServerPort
    private int port;

    @DisplayName("'/hello' endpoint should return expected response body text via HTTP protocol")
    @Test
    void shouldReturnExpectedGreetingsViaHTTP() {
        when()
                .get("http://localhost:" + port + "/hello")
        .then()
                .statusCode(is(200))
                .body(containsString("Hello World!"));
    }

    @DisplayName("'/hello/{lastName}' endpoint should return expected greeting for the person")
    @Test
    public void shouldReturnExpectedGreetingsForPerson() {
        Person johnDoe = new Person("John", "Doe");
        personRepository.save(johnDoe);
        
        when()
                .get("http://localhost:" + port + "/hello/Doe")
        .then()
                .statusCode(is(200))
                .body(containsString("Hello John Doe!"));
    }
}
