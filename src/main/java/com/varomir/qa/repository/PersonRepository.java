package com.varomir.qa.repository;

import com.varomir.qa.domain.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    Optional<Person> findByLastName(String lastName);
}
