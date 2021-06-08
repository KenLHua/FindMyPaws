package com.sg.findmypaws.dao;

import com.sg.findmypaws.model.Location;

import java.util.List;

public interface  LocationDao {
    public Location addLocation(Location location);
    public Location getLocationById(int id);
    public void updateLocation(Location location);
    public void deleteLocationById(int id);
    public List<Location> getAllLocations();
    public List<Location> getAllLocationsWithFilter(Location filter, int radius);
}
