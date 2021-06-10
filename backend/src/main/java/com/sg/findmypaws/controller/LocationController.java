package com.sg.findmypaws.controller;

import com.sg.findmypaws.dao.LocationDao;
import com.sg.findmypaws.model.Location;
import com.sg.findmypaws.service.LocationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    LocationDao locationDao;

    @Autowired
    LocationsService locationsService;

    @GetMapping("/locations")
    public List<Location> getAllLocations(){
        return locationsService.getAllLocations();
    }

    @PostMapping("/addLocation")
    public ResponseEntity<Location> addLocation(@RequestBody Location location){
        locationDao.addLocation(location);
        return new ResponseEntity<Location>(HttpStatus.CREATED);
    }

    @PutMapping("/updateLocation")
    public void updateLocation(@RequestBody Location location){
        locationDao.updateLocation(location);
    }

    @DeleteMapping("/deleteLocation/{id}")
    public ResponseEntity<Location> deleteLocation(@PathVariable int id){
        Location location = locationDao.getLocationById(id);
        if(location != null) {
            locationsService.deleteLocationById(id);
            return new ResponseEntity<Location>(HttpStatus.OK);
        }
        return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);

    }
    @GetMapping("/getLocation/{id}")
    public ResponseEntity<Location> getLocation(@PathVariable int id){
        Location location = locationDao.getLocationById(id);
        if(location != null) {
            return new ResponseEntity<Location>(location, HttpStatus.OK);
        }
        return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
    }


}
