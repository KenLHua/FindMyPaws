package com.sg.findmypaws.service;

import com.sg.findmypaws.dao.AnimalDao;
import com.sg.findmypaws.dao.LocationDao;
import com.sg.findmypaws.dao.OwnerDao;
import com.sg.findmypaws.dao.SightingDao;
import com.sg.findmypaws.model.Location;
import com.sg.findmypaws.model.Sighting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LocationsService {
    @Autowired
    AnimalDao animalDao;

    @Autowired
    SightingDao sightingDao;

    @Autowired
    OwnerDao ownerDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    JdbcTemplate jdbc;


    public void deleteLocationById(int id){
        List<Sighting> sightings =  sightingDao.getAllSightingsByLocId(id);
        for(Sighting sighting: sightings){
            try {
                sightingDao.deleteSightingById(sighting.getId());
            }catch (SQLException e){
            }
        }
        locationDao.deleteLocationById(id);
    }

    public List<Location> getAllLocations(){
        return locationDao.getAllLocations();
    }

    public boolean isLocationValid(Location loc){
        if(loc.getLatitude() != null && loc.getLongitude() != null && loc.getAddress() != null)
            return true;
        return false;
    }
}
