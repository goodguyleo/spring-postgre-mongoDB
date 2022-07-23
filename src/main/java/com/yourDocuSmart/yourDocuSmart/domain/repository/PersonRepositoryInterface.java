package com.yourDocuSmart.yourDocuSmart.domain.repository;


import com.yourDocuSmart.yourDocuSmart.domain.Person;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PersonRepositoryInterface {

    List<Person> getAll();
    Optional<List<Person>> getByAgeRange(Date compareDate);
    Optional<Person> getPerson(int documentId);
    Person save(Person person);
    void delete(int documentId);
}
