package com.sg.findmypaws.controller;

import com.sg.findmypaws.dao.SightingDaoDB;
import com.sg.findmypaws.model.Animal;
import com.sg.findmypaws.model.Filter;
import com.sg.findmypaws.model.Location;
import com.sg.findmypaws.model.Sighting;
import com.sg.findmypaws.service.CompositeService;
import com.sg.findmypaws.service.SightingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/sighting")
public class SightingController {

private final SightingsService service;
private final SightingDaoDB daoDB;
private final CompositeService compositeService;

    @Autowired
    public SightingController(SightingsService service, SightingDaoDB daoDB, CompositeService compositeService) {
        this.service = service;
        this.daoDB = daoDB;
        this.compositeService = compositeService;
    }

    @PostMapping("/add")
    public ResponseEntity<Sighting> addSighting(@RequestBody Sighting sighting) {
        return new ResponseEntity(daoDB.addSighting(sighting), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sighting> getSightingById(@PathVariable int id) throws SQLException {
        return new ResponseEntity(daoDB.getSightingById(id), HttpStatus.FOUND);
}

    @GetMapping("/all")
    public List<Sighting> getAllSightings() {
        return daoDB.getAllSightings();
    }

    // Location loc, int radius, int daysAgo, Animal mockAnimal


    @GetMapping("/filtered")
    public List<Sighting> getAllSightingsWithFilters(@RequestBody Filter filter) {
        return service.getAllFilteredSightings(filter.loc, filter.radius, filter.daysAgo, filter.mockAnimal);
}

   @GetMapping("/filtered/location/{locId}")
   public List<Sighting> getAllSightingsByLocId(@PathVariable int locId)  {
        return daoDB.getAllSightingsByLocId(locId);
}

    @GetMapping("/filtered/animal/{animalId}")
    public List<Sighting> getAllSightingsByAnimalId(@PathVariable int animalId) {
        return daoDB.getAllSightingsByAnimalId(animalId);
}

    @PutMapping("/update")
    public void updateSighting(@RequestBody Sighting sighting) throws SQLException{
        daoDB.updateSighting(sighting);
}

    @PostMapping("/delete/{id}")
    public void deleteSighting(@PathVariable int id) throws SQLException {
        daoDB.deleteSightingById(id);
}
    @GetMapping("/filtered/allRecent")
    public List<Sighting> getRecentSightingForAnimalWithFilters(@RequestBody Filter filter){
        return compositeService.getRecentSightingForAnimals(filter.loc, filter.radius, filter.daysAgo);
    }
}
