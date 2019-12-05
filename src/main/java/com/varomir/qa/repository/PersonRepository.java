package com.varomir.qa.repository;

import com.varomir.qa.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    Optional<Person> findByLastName(String lastName);
}
