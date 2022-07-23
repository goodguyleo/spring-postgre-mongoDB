package com.yourDocuSmart.yourDocuSmart.domain;

import lombok.Data;

import javax.persistence.Id;
import java.sql.Date;

@Data
public class Person {

    private int documentId;
    private String name;
    private String lastName;
    private String documentType;
    private Date birthDate;
    private String cityOfBirth;

}
