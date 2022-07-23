package com.yourDocuSmart.yourDocuSmart.domain.repository;

import com.yourDocuSmart.yourDocuSmart.domain.ImageMongo;
import java.util.List;
import java.util.Optional;

public interface ImageMongoRepositoryInterface {

    ImageMongo save(ImageMongo imageMongo);
    List<ImageMongo> getAllImage();
    Optional<ImageMongo> getImage(int id);
    void delete(int id);
}
