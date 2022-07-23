package com.yourDocuSmart.yourDocuSmart.persistence.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "personas")

public class PersonEntity {
    @Id
    @Column(name="document_id")
    private Integer documentIdEntity;

    @Column(name = "name")
    private String nameEntity;

    @Column(name = "last_name")
    private String lastNameEntity;

    @Column(name = "document_type")
    private String documentTypeEntity;

    @DateTimeFormat
    @Column(name = "birthday")
    private Date birthDateEntity;

    @Column(name = "city_of_birth")
    private String cityOfBirthEntity;

    public Integer getDocumentIdEntity() {
        return documentIdEntity;
    }

    public void setDocumentIdEntity(Integer documentIdEntity) {
        this.documentIdEntity = documentIdEntity;
    }

    public String getNameEntity() {
        return nameEntity;
    }

    public void setNameEntity(String nameEntity) {
        this.nameEntity = nameEntity;
    }

    public String getLastNameEntity() {
        return lastNameEntity;
    }

    public void setLastNameEntity(String lastNameEntity) {
        this.lastNameEntity = lastNameEntity;
    }

    public String getDocumentTypeEntity() {
        return documentTypeEntity;
    }

    public void setDocumentTypeEntity(String documentTypeEntity) {
        this.documentTypeEntity = documentTypeEntity;
    }

    public Date getBirthDateEntity() {
        return birthDateEntity;
    }

    public void setBirthDateEntity(Date birthDateEntity) {
        this.birthDateEntity = birthDateEntity;
    }

    public String getCityOfBirthEntity() {
        return cityOfBirthEntity;
    }

    public void setCityOfBirthEntity(String cityOfBirthEntity) {
        this.cityOfBirthEntity = cityOfBirthEntity;
    }

}
