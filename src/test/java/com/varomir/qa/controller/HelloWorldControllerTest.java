package com.varomir.qa.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Execution(ExecutionMode.CONCURRENT)
public class HelloWorldControllerTest {

    private HelloWorldController controllerUnderTest = new HelloWorldController();

    @DisplayName("'hello()' method should return empty string")
    @Test
    public void shouldReturnNotEmptyGreetings() {
        System.out.println(">> Thread_ID: " + Thread.currentThread().getId());
        assertFalse(controllerUnderTest.hello().isEmpty(),
                "Returned text from the controller was empty");
    }

    @DisplayName("'hello()' method should return expected greetings")
    @Test
    public void shouldReturnExpectedGreetings() {
        System.out.println(">> Thread_ID: " + Thread.currentThread().getId());
        assertEquals("Hello World!", controllerUnderTest.hello(),
                "Returned text from the controller was not as expected!");
    }
}
