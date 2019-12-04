package com.varomir.qa.controller;

import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.Assert.assertThat;

public class TestHelloWorldController {

    private HelloWorldController controllerUnderTest = new HelloWorldController();

    @Test
    public void shouldReturnExpectedGreetings() {
        assertEquals(controllerUnderTest.hello(), "Hello World!", "My error message here");
    }
}
