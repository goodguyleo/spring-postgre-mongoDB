package com.yourDocuSmart.yourDocuSmart.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;


@Document
public class ImageMongoEntity {

    @Id
    @Column(name = "id")
    private int idEntity;

    @Column(name = "asigned_image")
    private String asignedImageEntity;

    public ImageMongoEntity(int idEntity) {
        this.idEntity = idEntity;
    }

    public int getId() {
        return idEntity;
    }

    public void setId(int idEntity) {
        this.idEntity = idEntity;
    }

    public String getAsignedImageEntity() {
        return asignedImageEntity;
    }

    public void setAsignedImageEntity(String asignedImageEntity) {
        this.asignedImageEntity = asignedImageEntity;
    }
}
