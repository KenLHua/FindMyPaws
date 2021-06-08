package com.sg.findmypaws.dao;

import com.sg.findmypaws.model.Animal;
import com.sg.findmypaws.model.Sighting;

import java.util.List;

public interface SightingDao {
    public Sighting addSighting(Sighting sighting);
    public Sighting getSightingById(int id);
    public Sighting updateSighting(Sighting sighting);
    public Sighting deleteSightingById(int id);
    public List<Sighting> getAllSightings();

    // For top ten
    public List<Sighting> getAllFilteredSightingsByAnimal(Animal animal);
}
