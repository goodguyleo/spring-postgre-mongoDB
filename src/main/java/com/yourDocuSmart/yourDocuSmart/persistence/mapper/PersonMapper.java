package com.yourDocuSmart.yourDocuSmart.persistence.mapper;

import com.yourDocuSmart.yourDocuSmart.domain.Person;
import com.yourDocuSmart.yourDocuSmart.persistence.entity.PersonEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    @Mappings({
            @Mapping(source = "documentIdEntity", target = "documentId"),
            @Mapping(source = "nameEntity", target = "name"),
            @Mapping(source = "lastNameEntity", target = "lastName"),
            @Mapping(source = "documentTypeEntity", target = "documentType"),
            @Mapping(source = "birthDateEntity", target = "birthDate"),
            @Mapping(source = "cityOfBirthEntity", target = "cityOfBirth")

    })

    Person toPerson(PersonEntity personEntity);

    List<Person> toPersons(List<PersonEntity> persons);

    @InheritInverseConfiguration
    PersonEntity toPersonEntity(Person person);
}
