package com.sg.findmypaws.dao;

import com.sg.findmypaws.model.Animal;
import com.sg.findmypaws.model.Sighting;

import java.sql.SQLException;
import java.util.List;

public interface SightingDao {
    public Sighting addSighting(Sighting sighting);
    public Sighting getSightingById(int id) throws SQLException;
    public void updateSighting(Sighting sighting) throws SQLException;
    public void deleteSightingById(int id) throws SQLException;
    public List<Sighting> getAllSightings();

    List<Sighting> getAllSightingsByLocId(int locId);

    List<Sighting> getAllSightingsByAnimalId(int animalId);
}
