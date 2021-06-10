package com.sg.findmypaws.controller;

import com.sg.findmypaws.dao.AnimalDao;
import com.sg.findmypaws.model.Animal;
import com.sg.findmypaws.service.AnimalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/api/animal")
public class AnimalController {
    @Autowired
    AnimalDao animalDao;

    @Autowired
    AnimalsService animalsService;


    @GetMapping("/animals")
    public List<Animal> getAllAnimals(){
        return animalsService.getAllAnimals();
    }

    @GetMapping("/getAnimal/{id}")
    public Animal getAnimal(@PathVariable int id){
        try{
            Animal animal =animalsService.getAnimalById(id);
            //animal.setHash("Hidden hash");
            return animal;
        }catch (SQLException e){
            throw new EmptyResultDataAccessException(0);
        }
    }
    @PostMapping("/addAnimal")
    public ResponseEntity<Animal> addAnimal(@RequestBody Animal animal, HttpServletRequest request){
        try {
            animal = animalsService.addAnimal(animal);
            return new ResponseEntity<Animal>(animal, HttpStatus.CREATED);
        }catch (SQLIntegrityConstraintViolationException e){ // Owner does not exist for animal
            return new ResponseEntity<Animal>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch(SQLException e){
            return new ResponseEntity<Animal>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/deleteAnimal/{id}/{hash}")
    public ResponseEntity<Animal> deleteAnimal(@PathVariable int id, @PathVariable String hash) throws SQLException {
        hash = hash.replace("+", " ");
        Animal animal = animalDao.getAnimalById(id);
        if(animal.getHash().equals(hash)){
            animalsService.deleteAnimalById(id);
            return new ResponseEntity<Animal>(HttpStatus.OK);
        }
        return new ResponseEntity<Animal>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/editAnimal")
    public ResponseEntity<Animal> editAnimal(@RequestBody Animal animal, HttpServletRequest request)throws SQLException{
        try{
            animalDao.updateAnimal(animal);
            return new ResponseEntity<Animal>(HttpStatus.OK);
        }catch (SQLException e){
            return new ResponseEntity<Animal>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


}
