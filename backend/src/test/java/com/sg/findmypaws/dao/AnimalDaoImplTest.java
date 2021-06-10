package com.sg.findmypaws.dao;

import com.sg.findmypaws.TestApplicationConfiguration;
import com.sg.findmypaws.model.Animal;
import com.sg.findmypaws.model.Owner;
import com.sg.findmypaws.model.Sighting;
import com.sg.findmypaws.service.AnimalsService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class AnimalDaoImplTest {
    @Autowired
    AnimalsService animalsService;

    @Autowired
    AnimalDao animalDao;

    @Autowired
    SightingDao sightingDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    OwnerDao ownerDao;




    @BeforeEach
    void setup(){
        List<Sighting> sightings = sightingDao.getAllSightings();
        for( Sighting sighting : sightings){
            try {
                sightingDao.deleteSightingById(sighting.getId());
            }catch (SQLException e){

            }
        }
        List<Animal> animals = animalDao.getAllAnimals();
        for( Animal animal: animals){
            try{animalDao.deleteAnimalById(animal.getId());}catch(SQLException e){}

        }
        System.out.println("hello Ken");

        List<Owner> owners = ownerDao.getAllOwners();
        for( Owner owner : owners){
            ownerDao.deleteOwnerById(owner.getId());

        }
    }

    @Test
    void addAndGetAnimal() {

        Owner owner = new Owner();
        owner.setEmail("email@gmail.com");
        owner.setPhone("18001234567");
        owner.setName("Ken");
        owner = ownerDao.addOwner(owner);

        Animal animal = new Animal();
        animal.setOwner(owner);
        animal.setDate(LocalDate.now());

        animal.setStatus(0);
        animal.setName("Bill");
        animal.setBreed("german shepard");
        animal.setSpecies("dog");
        animal.setColor("color");
        animal.setDescription("desc");
        animal.setHash("hashstring");
        animal.setImage("image");
        animal.setHeight(10);
        animal.setWeight(10);
        animal.setAge(1);
        animal.setFemale(true);
        animal.setNameTag(true);

        animal = animalDao.addAnimal(animal);
        Animal fromDao = null;
        try {
            fromDao = animalDao.getAnimalById(animal.getId());
        }catch(SQLException e){
            assertFalse(true, "there was an error");
            System.err.println(e);
        }
        assertEquals(animal,fromDao);

    }

    @Test
    void updateAnimal() {
        Owner owner = new Owner();
        owner.setEmail("email@gmail.com");
        owner.setPhone("18001234567");
        owner.setName("Ken");
        owner = ownerDao.addOwner(owner);

        Animal animal = new Animal();
        animal.setOwner(owner);
        animal.setDate(LocalDate.now());
        animal.setStatus(0);
        animal.setName("Bill");
        animal.setBreed("german shepard");
        animal.setSpecies("dog");
        animal.setColor("color");
        animal.setDescription("desc");
        animal.setHash("hashstring");
        animal.setImage("image");
        animal.setHeight(10);
        animal.setWeight(10);
        animal.setAge(1);
        animal.setFemale(true);
        animal.setNameTag(true);

        animal = animalDao.addAnimal(animal);

        Animal fromDao = null;

        try {
            fromDao = animalDao.getAnimalById(animal.getId());
            fromDao.setOwner(animalsService.getOwnerForAnimal(fromDao));
        }catch(SQLException e){
            assertFalse(true, "there was an error");
            System.err.println(e);
        }

        System.out.println(animal.toString());
        System.out.println(fromDao.toString());


        assertEquals(animal,fromDao);

        animal.setName("Billie");
        try {
            fromDao = animalDao.getAnimalById(animal.getId());
        }catch(SQLException e){
            assertFalse(true, "there was an error");
            System.err.println(e);
        }
        assertNotEquals(animal,fromDao);

        try {
            animalDao.updateAnimal(animal);
        }catch(SQLException e){
            assertFalse(true, "there was an error");
            System.err.println(e);
        }
        try {
            fromDao = animalDao.getAnimalById(animal.getId());
            fromDao.setOwner(animalsService.getOwnerForAnimal(fromDao));
        }catch(SQLException e){
            assertFalse(true, "there was an error");
            System.err.println(e);
        }
        assertEquals(animal,fromDao);


    }

    @Test
    void getAllAnimals() {
        Owner owner = new Owner();
        owner.setEmail("email@gmail.com");
        owner.setPhone("18001234567");
        owner.setName("Ken");
        owner = ownerDao.addOwner(owner);

        Animal animal = new Animal();
        animal.setOwner(owner);
        animal.setDate(LocalDate.now());
        animal.setStatus(0);
        animal.setName("Bill");
        animal.setBreed("german shepard");
        animal.setSpecies("dog");
        animal.setColor("color");
        animal.setDescription("desc");
        animal.setHash("hashstring");
        animal.setImage("image");
        animal.setHeight(10);
        animal.setWeight(10);
        animal.setAge(1);
        animal.setFemale(true);
        animal.setNameTag(true);
        animalDao.addAnimal(animal);

        Owner owner2 = new Owner();
        owner2.setEmail("email2@gmail.com");
        owner2.setPhone("12003004000");
        owner2.setName("Tyler");
        owner2 = ownerDao.addOwner(owner2);

        Animal animal2 = new Animal();
        animal2.setOwner(owner2);
        animal2.setDate(LocalDate.now());
        animal2.setStatus(0);
        animal2.setName("Bill");
        animal2.setBreed("german shepard");
        animal2.setSpecies("dog");
        animal2.setColor("color");
        animal2.setDescription("desc");
        animal2.setHash("hashstring");
        animal2.setImage("image");
        animal2.setHeight(10);
        animal2.setWeight(10);
        animal2.setAge(1);
        animal2.setFemale(true);
        animal2.setNameTag(true);

        animalDao.addAnimal(animal2);
        List<Animal> animals = animalDao.getAllAnimals();
        assertTrue(animals.size() == 2);
        assertTrue(animals.contains(animal));
        assertTrue(animals.contains(animal2));
    }

    @Test
    void serviceDelete(){

    }
}