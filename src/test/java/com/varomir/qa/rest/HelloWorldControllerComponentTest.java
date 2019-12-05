package com.varomir.qa.rest;

import com.varomir.qa.DemoApp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DemoApp.class})
@WebMvcTest
@Execution(ExecutionMode.CONCURRENT)
public class HelloWorldControllerComponentTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldReturnHelloWorld() throws Exception {
        System.out.println(">> From 'HelloWorldControllerComponentTest.shouldReturnHelloWorld()' Thread_ID: " + Thread.currentThread().getId());

        mockMvc.perform(get("/hello"))
                .andExpect(content().string("Hello World!"));
    }
}
