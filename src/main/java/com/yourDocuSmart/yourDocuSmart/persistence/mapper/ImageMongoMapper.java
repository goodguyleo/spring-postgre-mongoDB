package com.yourDocuSmart.yourDocuSmart.persistence.mapper;

import com.yourDocuSmart.yourDocuSmart.domain.ImageMongo;
import com.yourDocuSmart.yourDocuSmart.persistence.entity.ImageMongoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMongoMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "asignedImageEntity", target = "asignedImage")
    })
    ImageMongo toImageMongo(ImageMongoEntity imageMongoEntity);
    List<ImageMongo> toImageMongo(List<ImageMongoEntity> imageMongoEntities);

    @InheritInverseConfiguration
    ImageMongoEntity toImageMongoEntity(ImageMongo imageMongo);

}
