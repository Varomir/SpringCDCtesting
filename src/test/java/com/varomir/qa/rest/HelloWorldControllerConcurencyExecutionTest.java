package com.varomir.qa.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.CONCURRENT)
public class HelloWorldControllerConcurencyExecutionTest {

    private HelloWorldController controllerUnderTest = new HelloWorldController();

    @DisplayName("'hello()' method should return non empty string")
    @Test
    public void shouldReturnNotEmptyGreetings() {
        System.out.println(">> From 'HelloWorldControllerConcurencyExecutionTest.shouldReturnNotEmptyGreetings()' Thread_ID: "
                + Thread.currentThread().getId() + ", Thread_activeCount: " + Thread.activeCount());

        assertTrue(controllerUnderTest.hello().length() > 0,
                "Returned text from the controller was empty");
    }

    @DisplayName("'hello()' method should return expected greetings")
    @Test
    public void shouldReturnExpectedGreetings() {
        System.out.println(">> From 'HelloWorldControllerConcurencyExecutionTest.shouldReturnExpectedGreetings()' Thread_ID: "
                + Thread.currentThread().getId() + ", Thread_activeCount: " + Thread.activeCount());

        assertEquals("Hello World!", controllerUnderTest.hello(),
                "Returned text from the controller was not as expected!");
    }
}
