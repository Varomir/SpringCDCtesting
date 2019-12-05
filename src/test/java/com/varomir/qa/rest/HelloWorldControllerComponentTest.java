package com.varomir.qa.rest;

import com.varomir.qa.domain.Person;
import com.varomir.qa.repository.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@ExtendWith(SpringExtension.class)
@WebMvcTest
// https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/testing.html#testcontext-parallel-test-execution
@Execution(ExecutionMode.SAME_THREAD)
public class HelloWorldControllerComponentTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository personRepository;


    @DisplayName("'/hello' endpoint should return expected greetings when GET")
    @Test
    void shouldReturnHelloWorld() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(content().string("Hello World!"));
    }

    @DisplayName("'/hello/{lastName}' endpoint should return expected greetings for the existing Person when GET")
    @Test
    void shouldReturnPersonFullName() throws Exception {
        Person johnDoe = new Person("John", "Doe");
        given(personRepository.findByLastName("Doe")).willReturn(Optional.of(johnDoe));

        mockMvc.perform(get("/hello/Doe"))
                .andExpect(content().string("Hello John Doe!"));
    }

    @DisplayName("'/hello/{lastName}' endpoint should return expected error greetings for the unknown Person when GET")
    @Test
    void shouldReturnMessageIfPersonIsUnknown() throws Exception {
        given(personRepository.findByLastName(anyString())).willReturn(Optional.empty());

        mockMvc.perform(get("/hello/Doe"))
                .andExpect(content().string("Who is this 'Doe' you're talking about?"));
    }
}
