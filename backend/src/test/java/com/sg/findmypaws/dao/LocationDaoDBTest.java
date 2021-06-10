package com.sg.findmypaws.dao;

import com.sg.findmypaws.TestApplicationConfiguration;
import com.sg.findmypaws.model.Animal;
import com.sg.findmypaws.model.Location;
import com.sg.findmypaws.model.Owner;
import com.sg.findmypaws.model.Sighting;
import com.sg.findmypaws.service.LocationsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class LocationDaoDBTest {

    @Autowired
    LocationsService locationsService;

    @Autowired
    LocationDao locationDao;

    @Autowired
    AnimalDao animalDao;

    @Autowired
    OwnerDao ownerDao;

    @Autowired
    SightingDao sightingDao;

    @BeforeEach
    void setup(){
        List<Sighting> sightings = sightingDao.getAllSightings();
        for( Sighting sight: sightings){
            try {
                sightingDao.deleteSightingById(sight.getId());
            }catch (SQLException e){}
        }
        List<Location> locations = locationDao.getAllLocations();
        for( Location loc: locations){
            locationDao.deleteLocationById(loc.getId());
        }
    }

    @Test
    void getAllLocationsWithFilter() {
        Location loc1 = new Location();
        loc1.setName("Howling Rays'");
        loc1.setAddress("727 N Broadway #128, Los Angeles, CA 90012");
        loc1.setLatitude( (float) 34.0613);
        loc1.setLongitude( (float) -118.239);
        loc1.setDescription("Description");

        loc1 = locationDao.addLocation(loc1);
        loc1 = locationDao.getLocationById(loc1.getId());

        Location loc2 = new Location();
        loc2.setName("Bonchon Fried Chicken");
        loc2.setAddress("710 W Las Tunas Dr, San Gabriel, CA 91776");
        loc2.setLatitude( (float) 34.1018);
        loc2.setLongitude( (float) -118.110);
        loc2.setDescription("Description");

        loc2 = locationDao.addLocation(loc2);
        loc2 = locationDao.getLocationById(loc2.getId());

        Location loc3 = new Location();
        loc3.setName("Rosemead HS");
        loc3.setAddress("9063 Mission Dr, Rosemead, CA 91770");
        loc3.setLatitude( (float) 34.0855);
        loc3.setLongitude( (float) -118.071);
        loc3.setDescription("Description");
        loc3 = locationDao.addLocation(loc3);
        loc3 = locationDao.getLocationById(loc3.getId());

        List<Location> locationList = locationDao.getAllLocationsWithFilter(loc1, 9);
        assertTrue(locationList.contains(loc2));
        assertFalse(locationList.contains(loc3));
    }

    @Test
    void addAndGetLocation() {
        Location loc = new Location();
        loc.setName("Howling Rays'");
        loc.setAddress("727 N Broadway #128, Los Angeles, CA 90012");
        loc.setLatitude( (float) 34.0613);
        loc.setLongitude( (float) -118.239);
        loc.setDescription("Description");

        loc = locationDao.addLocation(loc);

        Location fromDao = locationDao.getLocationById(loc.getId());
        assertEquals(loc,fromDao);
    }

    @Test
    void updateLocation() {
        Location loc = new Location();
        loc.setName("Howling Rays'");
        loc.setAddress("727 N Broadway #128, Los Angeles, CA 90012");
        loc.setLatitude( (float) 34.0613);
        loc.setLongitude( (float) -118.239);
        loc.setDescription("Description");

        loc = locationDao.addLocation(loc);

        Location fromDao = locationDao.getLocationById(loc.getId());
        assertEquals(loc,fromDao);

        loc.setDescription("Changed Description");
        assertNotEquals(loc,fromDao);

        locationDao.updateLocation(loc);
        fromDao = locationDao.getLocationById(loc.getId());
        assertEquals(loc, fromDao);
    }

    @Test
    void deleteLocationById() {
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

        Location loc = new Location();
        loc.setName("Howling Rays'");
        loc.setAddress("727 N Broadway #128, Los Angeles, CA 90012");
        loc.setLatitude( (float) 34.0613);
        loc.setLongitude( (float) -118.239);
        loc.setDescription("Description");
        loc = locationDao.addLocation(loc);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setLocationId(loc.getId());
        sighting.setAnimalId(animal.getId());
        sighting = sightingDao.addSighting(sighting);

        Sighting sightFromDao = null;
        try {
            sightFromDao = sightingDao.getSightingById(sighting.getId());
        }catch(SQLException e){}

        assertEquals(sightFromDao, sighting);

        locationsService.deleteLocationById(loc.getId());
        sightFromDao = null;
        try {
            sightFromDao = sightingDao.getSightingById(sighting.getId());
        }catch(SQLException e){}

        assertNull(sightFromDao);


    }
}