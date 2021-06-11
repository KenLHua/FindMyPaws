package com.sg.findmypaws.controller;

import com.sg.findmypaws.dao.AnimalDao;
import com.sg.findmypaws.dao.LocationDaoDB;
import com.sg.findmypaws.dao.OwnerDaoDB;
import com.sg.findmypaws.model.*;
import com.sg.findmypaws.service.AnimalsService;
import com.sg.findmypaws.service.CompositeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/composite")
public class CompositeController {

    @Autowired
    CompositeService compositeService;
    @Autowired
    AnimalsService animalService;
    @Autowired
    LocationDaoDB LDDB;



    @PostMapping("/lost")
    public List<Composite> getAllLostComposites(@RequestBody Filter filter) throws SQLException {
        List<Sighting> sightings = compositeService.getRecentSightingForAnimals(filter.loc, filter.radius, filter.daysAgo);
        List<Composite> composites = new ArrayList<>();
        for (Sighting s : sightings) {
            Animal a = animalService.getAnimalById(s.getAnimalId());
            if (a.getStatus() != 2) {
                continue;
            }
            Location l = LDDB.getLocationById(s.getLocationId());
            Owner o = a.getOwner();
            System.out.println(o.getId());
            composites.add(new Composite(a.getName(), s.getDate(), l.getLatitude(), l.getLongitude(), a.getId(), a.getDate(), a.getStatus(), a.getBreed(), a.getSpecies(), a.getColor(), a.getDescription(), a.getHash(), a.getImage(), a.getHeight(), a.getWeight(), a.getAge(), a.isFemale(),  a.hasNameTag(), o.getPhone(), o.getEmail()));
        }
        return composites;
    }

    @PostMapping("/found")
    public List<Composite> getAllFoundComposites(@RequestBody Filter filter) throws SQLException {
        List<Sighting> sightings = compositeService.getRecentSightingForAnimals(filter.loc, filter.radius, filter.daysAgo);
        List<Composite> composites = new ArrayList<>();
        for (Sighting s : sightings) {
            Animal a = animalService.getAnimalById(s.getAnimalId());
            if (a.getStatus() != 1) {
                continue;
            }

            Location l = LDDB.getLocationById(s.getLocationId());
            Owner o = a.getOwner();
            composites.add(new Composite(a.getName(), s.getDate(), l.getLatitude(), l.getLongitude(), a.getId(), a.getDate(), a.getStatus(), a.getBreed(), a.getSpecies(), a.getColor(), a.getDescription(), a.getHash(), a.getImage(), a.getHeight(), a.getWeight(), a.getAge(), a.isFemale(),  a.hasNameTag(), o.getPhone(), o.getEmail()));
        }
        return composites;
    }

    @PostMapping("/all")
    public List<Composite> getAllUniqueComposites(@RequestBody Filter filter) throws SQLException {
        List<Sighting> sightings = compositeService.getRecentSightingForAnimals(filter.loc, filter.radius, filter.daysAgo);
        List<Composite> composites = new ArrayList<>();
        for (Sighting s : sightings) {
            Animal a = animalService.getAnimalById(s.getAnimalId());
            Location l = LDDB.getLocationById(s.getLocationId());
            Owner o = a.getOwner();
            composites.add(new Composite(a.getName(), s.getDate(), l.getLatitude(), l.getLongitude(), a.getId(), a.getDate(), a.getStatus(), a.getBreed(), a.getSpecies(), a.getColor(), a.getDescription(), a.getHash(), a.getImage(), a.getHeight(), a.getWeight(), a.getAge(), a.isFemale(),  a.hasNameTag(), o.getPhone(), o.getEmail()));
        }
        return composites;
    }
}
