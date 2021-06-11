package com.sg.findmypaws.service;

import com.sg.findmypaws.model.Animal;
import com.sg.findmypaws.model.Location;
import com.sg.findmypaws.model.Sighting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompositeService {
    @Autowired
    AnimalsService animalsService;

    @Autowired
    SightingsService sightingsService;

    public List<Sighting> getRecentSightingForAnimals(Location location, int radius, int daysAgo){
        List<Animal> animals = animalsService.getAllAnimals();
        List<Sighting> mostRecentSightings = new ArrayList<>();
        for( Animal an: animals){
            List<Sighting> list = sightingsService.getAllFilteredSightings(location, radius, daysAgo, an);
            Sighting mostRecent = null;
            if(list.size() > 0){
                mostRecent = list.get(0);
            }
            for(int i = 1; i < list.size()-1; i++){
                if(mostRecent.getDate().isAfter(list.get(i).getDate()))
                    mostRecent = list.get(i);
            }
            if(mostRecent != null)
                mostRecentSightings.add(mostRecent);
        }
        return mostRecentSightings;


    }
}
