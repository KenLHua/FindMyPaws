package com.sg.superhero.dao;

import com.sg.superhero.model.Location;

import java.util.List;

public interface LocationDao {
    Location getLocationById(int id);
    List<Location> getAllLocations();
    Location addLocation(Location location);
    void updateLocation(Location location);
    void deleteLocationById(int id);
}
