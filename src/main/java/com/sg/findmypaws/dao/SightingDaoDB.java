package com.sg.findmypaws.dao;

import com.sg.findmypaws.model.Animal;
import com.sg.findmypaws.model.Sighting;

import java.util.List;

public class SightingDaoDB implements SightingDao{
    @Override
    public Sighting addSighting(Sighting sighting) {
        return null;
    }

    @Override
    public Sighting getSightingById(int id) {
        return null;
    }

    @Override
    public Sighting updateSighting(Sighting sighting) {
        return null;
    }

    @Override
    public Sighting deleteSightingById(int id) {
        return null;
    }

    @Override
    public List<Sighting> getAllSightings() {
        return null;
    }

    @Override
    public List<Sighting> getAllFilteredSightingsByAnimal(Animal animal) {
        return null;
    }
}
