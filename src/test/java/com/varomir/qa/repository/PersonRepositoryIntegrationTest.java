package com.varomir.qa.repository;

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
public class PersonRepositoryIntegrationTest {

    @Autowired
    private PersonRepository repositoryUnderTest;


    @DisplayName("'PersonRepository' should be able to deal with DataBase")
    @Test
    public void shouldSaveAndFetchPerson() {
        System.out.println(">> From 'PersonRepositoryIntegrationTest.shouldSaveAndFetchPerson()' Thread_ID: "
                + Thread.currentThread().getId());

        Person ivan = new Person("Ivan", "Ivanov");
        repositoryUnderTest.save(ivan);

        Optional<Person> maybeIvan = repositoryUnderTest.findByLastName("Ivanov");
        
        assertTrue(maybeIvan.isPresent(), "Couldn't fetch the Person by it's Lastname!");
        assertEquals("Ivanov", maybeIvan.get().getLastName(), "Extracte Person Lastname was not as expected!");
    }
}
