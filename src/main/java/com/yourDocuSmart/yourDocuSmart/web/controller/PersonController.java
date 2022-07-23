package com.yourDocuSmart.yourDocuSmart.web.controller;

import com.yourDocuSmart.yourDocuSmart.Exception.ExceptionHandlerEmptyImput;
import com.yourDocuSmart.yourDocuSmart.Exception.ExceptionHandlerNotFound;
import com.yourDocuSmart.yourDocuSmart.domain.service.ImageMongoService;
import com.yourDocuSmart.yourDocuSmart.domain.service.PersonService;
import com.yourDocuSmart.yourDocuSmart.domain.ImageMongo;
import com.yourDocuSmart.yourDocuSmart.domain.Person;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private ImageMongoService imageMongoService;

    @Autowired
    private PersonService personService;

    @GetMapping("/all")
    @ApiOperation("Return a List of all Persons")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Person>> getAll(){
        return new ResponseEntity<>(personService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/allImages")
    @ApiOperation("Return a List of all Images")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<ImageMongo>> getAllImage(){
        return new ResponseEntity<>(imageMongoService.getAllImage(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Gets a person by his ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT FOUND")
    })
    public Optional<Person> getPerson(@ApiParam(value = "the dniId of the person to find", required = true, example = "1090494068")
                                            @PathVariable("id")int documentId){
        if (!personService.getPerson(documentId).isPresent()){
            throw new ExceptionHandlerNotFound("persona no encontrada");
        } else {
            return personService.getPerson(documentId);
        }
    }

    @GetMapping("/findByAge/{compareDate}")
    @ApiOperation("Gets a List person younger than a setet Date (YYYY-MM-DD format)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT FOUND")
    })
    public ResponseEntity<List<Person>> getByAgeRange(@PathVariable("compareDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date compareDate){

        return personService.getByAge(compareDate)
                .map(personList -> new ResponseEntity<>(personList, HttpStatus.OK)
                ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping("/save")
    @ApiOperation("Save a new Person")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    public ResponseEntity<Person> save(@RequestBody Person person){
        if (person.getDocumentId()== 0 ||
                person.getName() == null ||
                person.getBirthDate() == null ||
                person.getLastName() == null ||
                person.getDocumentType() == null ||
                person.getCityOfBirth() == null){
            throw new ExceptionHandlerEmptyImput("por favor asegurese de llenar todos los campos");
        }
        return new ResponseEntity<>(personService.save(person), HttpStatus.CREATED);
    }

    @PostMapping("/saveImage")
    @ApiOperation("Saves an image if the user exist, to this, the image would have the same Id than the person")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    public ResponseEntity<ImageMongo> save(@RequestBody ImageMongo imageMongo){
        if (imageMongo.getId()==0 ||
                imageMongo.getAsignedImage().isEmpty()){
            throw new ExceptionHandlerEmptyImput("porfavor asegurese de llenar todos los campos");
        } else if (getPerson(imageMongo.getId()).isPresent()){
            return new ResponseEntity<>(imageMongoService.save(imageMongo), HttpStatus.CREATED);
        }
        return null;
    }

    @PutMapping("/editPerson")
    @ApiOperation("Chage data of a person just if it's finded on the DB")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "BAD REQUEST")
    })
    public ResponseEntity<Person> edit(@RequestBody Person person){
        return new ResponseEntity<>(personService.edit(person),HttpStatus.OK);
    }

    @PutMapping("/changeImage")
    @ApiOperation("Chage a image if it's finded on the DB")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    public ResponseEntity<Person> changeImage(@RequestBody ImageMongo imageMongo){
        return new ResponseEntity<>(imageMongoService.edit(imageMongo),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delets a person and his image by his ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT FOUND")
    })
    public ResponseEntity delete(@PathVariable("id")int documentId){
        if (personService.delete(documentId)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
             throw new ExceptionHandlerNotFound("no existe persona para eliminar");
        }
    }

    @DeleteMapping("/deleteImge/{id}")
    @ApiOperation("Deletes a image by his ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT FOUND")
    })
    public ResponseEntity deleteImage(@PathVariable("id")int id){
        if (imageMongoService.delete(id)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
