package com.sg.findmypaws.controller;

import com.sg.findmypaws.dao.AnimalDao;
import com.sg.findmypaws.dao.LocationDaoDB;
import com.sg.findmypaws.model.*;
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
    AnimalDao animalDao;
    @Autowired
    LocationDaoDB LDDB;



    @PostMapping("/lost")
    public List<Composite> getAllLostComposites(@RequestBody Filter filter) throws SQLException {
        List<Sighting> sightings = compositeService.getRecentSightingForAnimals(filter.loc, filter.radius, filter.daysAgo);
        List<Composite> composites = new ArrayList<>();
        for (Sighting s : sightings) {
            Animal a = animalDao.getAnimalById(s.getAnimalId());
            if (a.getStatus() != 2) {
                continue;
            }
            Location l = LDDB.getLocationById(s.getLocationId());
            composites.add(new Composite(a.getName(), s.getDate(), l.getLatitude(), l.getLongitude()));
        }
        return composites;
    }

    @PostMapping("/found")
    public List<Composite> getAllFoundComposites(@RequestBody Filter filter) throws SQLException {
        List<Sighting> sightings = compositeService.getRecentSightingForAnimals(filter.loc, filter.radius, filter.daysAgo);
        List<Composite> composites = new ArrayList<>();
        for (Sighting s : sightings) {
            Animal a = animalDao.getAnimalById(s.getAnimalId());
            if (a.getStatus() != 1) {
                continue;
            }
            Location l = LDDB.getLocationById(s.getLocationId());
            composites.add(new Composite(a.getName(), s.getDate(), l.getLatitude(), l.getLongitude()));
        }
        return composites;
    }

    @PostMapping("/all")
    public List<Composite> getAllUniqueComposites(@RequestBody Filter filter) throws SQLException {
        List<Sighting> sightings = compositeService.getRecentSightingForAnimals(filter.loc, filter.radius, filter.daysAgo);
        List<Composite> composites = new ArrayList<>();
        for (Sighting s : sightings) {
            Animal a = animalDao.getAnimalById(s.getAnimalId());
            Location l = LDDB.getLocationById(s.getLocationId());
            composites.add(new Composite(a.getName(), s.getDate(), l.getLatitude(), l.getLongitude()));
        }
        return composites;
    }
}
