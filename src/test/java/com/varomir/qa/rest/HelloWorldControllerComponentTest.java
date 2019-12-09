package com.varomir.qa.rest;

import com.github.javafaker.Name;
import com.varomir.qa.commons.utils.WithFaker;
import com.varomir.qa.domain.Person;
import com.varomir.qa.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
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
public class HelloWorldControllerComponentTest implements WithFaker {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository personRepository;
    private String firstName, lastName;

    @BeforeEach
    public void setUp() {
        Name fakePersonName = getFakePersonName();
        firstName = fakePersonName.firstName();
        lastName = fakePersonName.lastName();
    }


    @DisplayName("'/hello' endpoint should return expected greetings when GET")
    @Test
    void shouldReturnHelloWorld() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(content().string("Hello World!"));
    }

    @DisplayName("'/hello/${lastName}' endpoint should return expected greetings for the existing Person when GET")
    @Test
    void shouldReturnPersonFullName() throws Exception {
        Person fakePerson = new Person(firstName, lastName);
        given(personRepository.findByLastName(lastName)).willReturn(Optional.of(fakePerson));

        mockMvc.perform(get(String.format("/hello/%s", lastName)))
                .andExpect(content().string(String.format("Hello %s %s!", firstName, lastName)));
    }

    @DisplayName("'/hello/${lastName}' endpoint should return expected error greetings for the unknown Person when GET")
    @Test
    void shouldReturnMessageIfPersonIsUnknown() throws Exception {
        given(personRepository.findByLastName(anyString())).willReturn(Optional.empty());

        mockMvc.perform(get("/hello/" + lastName))
                .andExpect(content().string(String.format("Who is this '%s' you're talking about?", lastName)));
    }
}
