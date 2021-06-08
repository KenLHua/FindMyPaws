package com.sg.findmypaws.dao;

import com.sg.findmypaws.model.Location;

import java.util.List;

public interface  LocationDao {
    public Location addLocation(Location location);
    public Location getLocationById(int id);
    public Location updateLocation(Location location);
    public Location deleteLocationById(int id);
    public List<Location> getAllLocations();
    public List<Location> getAllFilteredLocations(Location filter);
}
