package com.varomir.qa.repository;

import com.github.javafaker.Name;
import com.varomir.qa.commons.utils.WithFaker;
import com.varomir.qa.domain.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PersonRepositoryIntegrationTest implements WithFaker {

    @Autowired
    private PersonRepository repositoryUnderTest;
    private String firstName, lastName;


    @DisplayName("'PersonRepository' should be able to deal with DataBase")
    @Test
    public void shouldSaveAndFetchPerson() {
        Name fakePersonName = getFakePersonName();
        firstName = fakePersonName.firstName();
        lastName = fakePersonName.lastName();
        Person fakePerson = new Person(firstName, lastName);
        repositoryUnderTest.save(fakePerson);

        Optional<Person> maybeFakePerson = repositoryUnderTest.findByLastName(lastName);

        assertTrue(maybeFakePerson.isPresent(), "Couldn't fetch the Person by it's LastName!");
        assertEquals(lastName, maybeFakePerson.get().getLastName(), "Extracted Person LastName was not as expected!");
    }
}
