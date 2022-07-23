package com.yourDocuSmart.yourDocuSmart.persistence;

import com.yourDocuSmart.yourDocuSmart.domain.Person;
import com.yourDocuSmart.yourDocuSmart.domain.repository.PersonRepositoryInterface;
import com.yourDocuSmart.yourDocuSmart.persistence.crud.PersonCrudRepository;
import com.yourDocuSmart.yourDocuSmart.persistence.entity.PersonEntity;
import com.yourDocuSmart.yourDocuSmart.persistence.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository implements PersonRepositoryInterface {

    @Autowired
    private PersonCrudRepository personCrudRepository;

    @Autowired
    private PersonMapper mapper;

    @Override
    public List<Person> getAll() {
        List<PersonEntity> persons = (List<PersonEntity>) personCrudRepository.findAll();
        return mapper.toPersons(persons);
    }

    @Override
    public Optional<List<Person>> getByAgeRange(Date compareDate) {

        Optional<List<PersonEntity>> personEntityList = personCrudRepository.findAllByBirthDateEntityAfter(compareDate);
        return personEntityList.map(person1 -> mapper.toPersons(person1));
    }

    @Override
    public Optional<Person> getPerson(int documentId) {
        Optional<PersonEntity> person = personCrudRepository.findById(documentId);
        return person.map(person1 -> mapper.toPerson(person1));
    }

    @Override
    public Person save(Person person) {
        PersonEntity personEntity = mapper.toPersonEntity(person);
        return mapper.toPerson(personCrudRepository.save(personEntity));
    }

    @Override
    public void delete(int documentId) {

        personCrudRepository.deleteById(documentId);
    }
}
