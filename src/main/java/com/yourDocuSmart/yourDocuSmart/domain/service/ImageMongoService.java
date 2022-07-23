package com.yourDocuSmart.yourDocuSmart.domain.service;

import com.yourDocuSmart.yourDocuSmart.domain.ImageMongo;
import com.yourDocuSmart.yourDocuSmart.domain.Person;
import com.yourDocuSmart.yourDocuSmart.domain.repository.ImageMongoRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageMongoService {

    @Autowired
    private ImageMongoRepositoryInterface imageMongoRepositoryInterface;

    public ImageMongo save(ImageMongo imageMongo){
        return imageMongoRepositoryInterface.save(imageMongo);
    }

    public List<ImageMongo> getAllImage(){
        return imageMongoRepositoryInterface.getAllImage();
    }

    public Optional<ImageMongo> getImage(int id){
        return imageMongoRepositoryInterface.getImage(id);
    }

    public Person edit(ImageMongo imageMongo){
        imageMongoRepositoryInterface.getImage(imageMongo.getId()).ifPresent(imageMongo1 -> {
            imageMongoRepositoryInterface.save(imageMongo);
        });
        System.out.println("no existe dicha persona para editar foto");
        return null;
    }


    public boolean delete(int id){
        return getImage(id).map(image -> {
            imageMongoRepositoryInterface.delete(id);
            System.out.println("se ha eliminado la imagen");
            return true;
        }).orElse(false);
    }
}
