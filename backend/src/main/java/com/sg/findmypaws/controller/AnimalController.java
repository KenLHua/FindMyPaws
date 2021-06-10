package com.sg.findmypaws.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sg.findmypaws.dao.AnimalDao;
import com.sg.findmypaws.model.Animal;
import com.sg.findmypaws.model.Location;
import com.sg.findmypaws.model.Sighting;
import com.sg.findmypaws.service.AnimalsService;
import com.sg.findmypaws.service.LocationsService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/animal")
public class AnimalController {
    @Autowired
    AnimalDao animalDao;

    @Autowired
    AnimalsService animalsService;

    @Autowired
    LocationsService locationsService;


    @GetMapping("/animals")
    public List<Animal> getAllAnimals(){
        return animalsService.getAllAnimals();
    }

    @GetMapping("/getAnimal/{id}")
    public Animal getAnimal(@PathVariable int id){
        try{
            Animal animal =animalsService.getAnimalById(id);
            // Uncomment out below if you want to hide hash code

            //animal.setHash("Hidden hash");
            return animal;
        }catch (SQLException e){
            throw new EmptyResultDataAccessException(0);
        }
    }





    @PostMapping("/addAnimal")
    public ResponseEntity<Animal> addAnimal(@RequestBody AnimalInputWrapper inputWrapper) throws DataIntegrityViolationException {
        System.out.println(inputWrapper);
        Location location = inputWrapper.location;
        Animal animal = inputWrapper.animal;
        if(!locationsService.isLocationValid(location)){
            throw new DataIntegrityViolationException("Location is invalid");
        }
        if(!inputWrapper.date.isCustomDateValid()){
            throw new DataIntegrityViolationException("Date is invalid");
        }
        if(!animalsService.isAnimalValid(animal)){
            throw new DataIntegrityViolationException("Animal is invalid");
        }
        if(!animalsService.isOwnerValid(animal.getOwner())) {
            throw new DataIntegrityViolationException("Owner is invalid");
        }

        LocalDate date = LocalDate.of(inputWrapper.date.year, inputWrapper.date.month, inputWrapper.date.day);



        try {
            animalsService.addAnimal(location, date, animal);
            return new ResponseEntity<>(animal, HttpStatus.OK);
        }catch (SQLIntegrityConstraintViolationException e){
            return new ResponseEntity<Animal>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (SQLException e){
            e.printStackTrace();
            return new ResponseEntity<Animal>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/deleteAnimal/{id}/{hash}")
    public ResponseEntity<Animal> deleteAnimal(@PathVariable int id, @PathVariable String hash) throws SQLException {
        // Converts + to spaces
        hash = hash.replace("+", " ");

        Animal animal = animalDao.getAnimalById(id);
        if(animal.getHash().equals(hash)){
            animalsService.deleteAnimalById(id);
            return new ResponseEntity<Animal>(HttpStatus.OK);
        }
        return new ResponseEntity<Animal>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/editAnimal")
    public ResponseEntity<Animal> editAnimal(@RequestBody Animal animal) throws DataIntegrityViolationException{
        if(!animalsService.isAnimalValid(animal)){
            throw new DataIntegrityViolationException("Invalid Animal");
        }
        animal.setHash(animal.getHash().replace("+", " "));

        try{
            Animal fromDao = animalsService.getAnimalById(animal.getId());
            if(!fromDao.getOwner().equals(animal.getOwner())){
                return new ResponseEntity<Animal>(HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }catch (SQLException e){
            return new ResponseEntity<Animal>(HttpStatus.NOT_FOUND);
        }

        

        try{
            animalsService.updateAnimal(animal);
            return new ResponseEntity<Animal>(HttpStatus.OK);
        }catch (AccessDeniedException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        }
    }

    public static class AnimalInputWrapper {
        Location location;
        CustomDate date;
        Animal animal;

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public CustomDate getDate() {
            return date;
        }

        public void setDate(CustomDate date) {
            this.date = date;
        }

        public Animal getAnimal() {
            return animal;
        }

        public void setAnimal(Animal animal) {
            this.animal = animal;
        }

        @Override
        public String toString() {
            return "AnimalInputWrapper{" +
                    "location=" + location +
                    ", date=" + date +
                    ", animal=" + animal +
                    '}';
        }
    }
    public static class CustomDate {
        Integer year, month, day;

        public Integer getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public Integer getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public Integer getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        @Override
        public String toString() {
            return "CustomDate{" +
                    "year=" + year +
                    ", month=" + month +
                    ", day=" + day +
                    '}';
        }
        public boolean isCustomDateValid(){
            if(year != null && month != null && day != null){
                return true;
            }
            return false;
        }
    }


}
