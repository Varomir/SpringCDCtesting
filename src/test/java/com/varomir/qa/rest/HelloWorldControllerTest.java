package com.varomir.qa.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@Execution(ExecutionMode.CONCURRENT)
public class HelloWorldControllerTest {

    @Autowired
    HelloWorldController controllerUnderTest;

    @DisplayName("'hello()' method should return empty string")
    @Test
    public void shouldReturnNotEmptyGreetings() {
        System.out.println(">> From 'HelloWorldControllerTest.shouldReturnNotEmptyGreetings()' Thread_ID: " + Thread.currentThread().getId());

        assertFalse(controllerUnderTest.hello().isEmpty(),
                "Returned text from the controller was empty");
    }

    @DisplayName("'hello()' method should return expected greetings")
    @Test
    public void shouldReturnExpectedGreetings() {
        System.out.println(">> From 'HelloWorldControllerTest.shouldReturnExpectedGreetings()' Thread_ID: " + Thread.currentThread().getId());

        assertEquals("Hello World!", controllerUnderTest.hello(),
                "Returned text from the controller was not as expected!");
    }
}
