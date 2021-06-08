package com.sg.superhero.dao;

import com.sg.superhero.model.Hero;
import com.sg.superhero.model.Location;
import com.sg.superhero.model.Sighting;

import java.sql.Timestamp;
import java.util.List;

public interface SightingDao {
    Sighting getSightingById(int id);
    List<Sighting> getAllSighting();
    Sighting addSighting(Sighting sighting);
    void updateSighting(Sighting sighting);
    void deleteSightingById(int id);
    Hero getHeroForSighting(int id);
    Location getLocationForSighting(int id);
}
