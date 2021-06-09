package com.sg.findmypaws.service;

import com.sg.findmypaws.dao.AnimalDaoDB;
import com.sg.findmypaws.dao.LocationDaoDB;
import com.sg.findmypaws.dao.SightingDaoDB;
import com.sg.findmypaws.model.Animal;
import com.sg.findmypaws.model.Location;
import com.sg.findmypaws.model.Sighting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

@Service
public class SightingsService {

    @Autowired
    SightingDaoDB sightingDaoDB;
    @Autowired
    LocationDaoDB locationDaoDB;
    @Autowired
    AnimalsService animalsService;
    @Autowired
    AnimalDaoDB animalDaoDB;

    private boolean betweenTwoDates(LocalDate date, LocalDate now, LocalDate past) {
        return date.isAfter(past) && date.isBefore(now);
    }


    public List<Sighting> getAllFilteredSightings(Location loc, int radius, int daysAgo, Animal mockAnimal) {
        List<Sighting> sightings = new ArrayList<>();
        if (loc != null) {
            List<Location> locs = locationDaoDB.getAllLocationsWithFilter(loc, radius);
            for (Location l : locs) {
                sightings.addAll(sightingDaoDB.getAllSightingsByLocId(l.getId()));
            }
        }
        else {
            sightings = sightingDaoDB.getAllSightings();
        }

        if (daysAgo != -1) {
            LocalDate now = LocalDate.now();
            LocalDate past = now.minusDays(daysAgo);
            sightings.removeIf(s -> !betweenTwoDates(s.date, now, past));
        }

        if (mockAnimal != null)
        {
            sightings.removeIf(s -> animalsService.compareAnimal(
                    animalDaoDB.getAnimalById(s.getAnimalId()) , mockAnimal));
        }

        return sightings;
    }


}
