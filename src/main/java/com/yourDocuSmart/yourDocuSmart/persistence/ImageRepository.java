package com.yourDocuSmart.yourDocuSmart.persistence;

import com.yourDocuSmart.yourDocuSmart.domain.ImageMongo;
import com.yourDocuSmart.yourDocuSmart.domain.repository.ImageMongoRepositoryInterface;
import com.yourDocuSmart.yourDocuSmart.persistence.crud.ImageMongoCrudRepository;
import com.yourDocuSmart.yourDocuSmart.persistence.crud.PersonCrudRepository;
import com.yourDocuSmart.yourDocuSmart.persistence.entity.ImageMongoEntity;
import com.yourDocuSmart.yourDocuSmart.persistence.entity.PersonEntity;
import com.yourDocuSmart.yourDocuSmart.persistence.mapper.ImageMongoMapper;
import com.yourDocuSmart.yourDocuSmart.persistence.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ImageRepository implements ImageMongoRepositoryInterface {


    @Autowired
    private PersonCrudRepository personCrudRepository;

    @Autowired
    private ImageMongoCrudRepository imageMongoCrudRepository;


    @Autowired
    private ImageMongoMapper mapperImage;


    @Override
    public List<ImageMongo> getAllImage(){
        List<ImageMongoEntity> images = (List<ImageMongoEntity>) imageMongoCrudRepository.findAll();
        return mapperImage.toImageMongo(images);
    }

    @Override
    public Optional<ImageMongo> getImage(int id){
        Optional<ImageMongoEntity> image = imageMongoCrudRepository.findById(id);
        return  image.map(imageMongo -> mapperImage.toImageMongo(imageMongo));
    }

    @Override
    public ImageMongo save(ImageMongo imageMongo){

        ImageMongoEntity imageMongoEntity = mapperImage.toImageMongoEntity(imageMongo);

        personCrudRepository.findByDocumentIdEntity(imageMongo.getId()).ifPresent(person -> {
                 mapperImage.toImageMongo(imageMongoCrudRepository.save(imageMongoEntity));
        });
        return null;
    }

    @Override
    public void delete(int id){

        imageMongoCrudRepository.deleteById(id);
    }
}
