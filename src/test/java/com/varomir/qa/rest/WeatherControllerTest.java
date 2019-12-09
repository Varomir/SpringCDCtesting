package com.varomir.qa.rest;

import com.github.javafaker.Name;
import com.varomir.qa.commons.utils.WithFaker;
import com.varomir.qa.domain.Person;
import com.varomir.qa.repository.PersonRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.anyString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;


@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@Execution(ExecutionMode.CONCURRENT)
public class WeatherControllerTest implements WithFaker {

    private WeatherController controllerUnderTest;
    private String firstName, lastName;

    @Mock
    private PersonRepository personRepository;


    @BeforeEach
    public void setUp() {
        initMocks(this);
        Name fakePersonName = getFakePersonName();
        firstName = fakePersonName.firstName();
        lastName = fakePersonName.lastName();
        controllerUnderTest = new WeatherController(personRepository);
    }

    @DisplayName("'hello()' method should return expected greetings")
    @Test
    public void shouldReturnExpectedGreetings() {
        assertEquals("Hello World!", controllerUnderTest.hello(),
                "Returned text from the controller was not as expected!");
    }

    @DisplayName("'hello($lastName)' method should return fullName of an existing Person")
    @Test
    public void shouldReturnFullNameOfAPerson() {
        Person fakePerson = new Person(firstName, lastName);
        given(personRepository.findByLastName(lastName)).willReturn(Optional.of(fakePerson));

        String greeting = controllerUnderTest.hello(lastName);

        assertEquals(String.format("Hello %s %s!", firstName, lastName), greeting,
                "Returned successfully greetings text from the controller was not as expected!");
    }

    @DisplayName("'hello(lastName)' method should return error greetings of an unknown Person")
    @Test
    public void shouldReturnMessageIfPersonIsUnknown() {
        given(personRepository.findByLastName(anyString())).willReturn(Optional.empty());

        String greeting = controllerUnderTest.hello("Doe");

        assertEquals("Who is this 'Doe' you're talking about?", greeting,
                "Returned error greetings text from the controller was not as expected!");
    }
}
