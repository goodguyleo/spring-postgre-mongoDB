package com.yourDocuSmart.yourDocuSmart.persistence.crud;

import com.yourDocuSmart.yourDocuSmart.persistence.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonCrudRepository extends CrudRepository <PersonEntity, Integer> {

    Optional<List<PersonEntity>> findAllByBirthDateEntityAfter(Date birthDate);

    Optional<PersonEntity> findByDocumentIdEntity(Integer integer);
}
