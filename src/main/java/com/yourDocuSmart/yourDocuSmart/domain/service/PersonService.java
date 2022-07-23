package com.yourDocuSmart.yourDocuSmart.domain.service;

import com.yourDocuSmart.yourDocuSmart.domain.Person;
import com.yourDocuSmart.yourDocuSmart.domain.repository.PersonRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepositoryInterface personRepositoryInterface;

    @Autowired
    private ImageMongoService imageMongoService;

    public List<Person> getAll() {
        return personRepositoryInterface.getAll();
    }

    public Optional<Person> getPerson(int documentId){
        return personRepositoryInterface.getPerson(documentId);
    }

    public Person edit(Person person){
        personRepositoryInterface.getPerson(person.getDocumentId()).ifPresent(person1 -> {
            personRepositoryInterface.save(person);
        });
        return null;
    }



    public Optional<List<Person>> getByAge(Date compareDate){
        return personRepositoryInterface.getByAgeRange(compareDate);
    }
    public Person save(Person person){
        System.out.println("se ha guardado a la persona exitosamente");
        return personRepositoryInterface.save(person);

    }

    public boolean delete(int documentId){
        return getPerson(documentId).map(person -> {

            imageMongoService.delete(documentId);
            personRepositoryInterface.delete(documentId);
            System.out.println("se ha eliminado la persona");
            return true;
        }).orElse(false);
    }

}
