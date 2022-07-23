package com.yourDocuSmart.yourDocuSmart.persistence.crud;

import com.yourDocuSmart.yourDocuSmart.persistence.entity.ImageMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageMongoCrudRepository extends MongoRepository<ImageMongoEntity, Integer> {

}
