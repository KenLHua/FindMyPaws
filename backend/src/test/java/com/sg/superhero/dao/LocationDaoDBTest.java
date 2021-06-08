package com.sg.superhero.dao;

import com.sg.superhero.model.Hero;
import com.sg.superhero.model.Location;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationDaoDBTest {
    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    HeroDao heroDao;

    @Autowired
    LocationDao locationDao;

    @Before
    public void setUp() {
        List<Location> locations = locationDao.getAllLocations();
        for (Location location : locations) {
            locationDao.deleteLocationById(location.getId());
        }
    }

    @Test
    public void testAddAndGetLocationById() {

        Location location = new Location();
        location.setAddress("California");
        location.setName("Location name");
        location.setLongitude((float) 0.55);
        location.setLatitude((float) 0.11);
        location = locationDao.addLocation(location);

        Location fromDao = locationDao.getLocationById(location.getId());
        assertEquals(location, fromDao);
    }

    @Test
    public void testGetAllLocations() {
        Location location = new Location();
        location.setAddress("California");
        location.setName("Location name");
        location.setLongitude((float) 0.55);
        location.setLatitude((float) 0.11);
        location = locationDao.addLocation(location);

        Location location2 = new Location();
        location2.setAddress("California 2");
        location2.setName("Location name 2");
        location2.setLongitude((float) 0.22);
        location2.setLatitude((float) 0.33);
        location2 = locationDao.addLocation(location2);

        List<Location> locationList = locationDao.getAllLocations();

        assertEquals(2, locationList.size());
        assertTrue(locationList.contains(location));
        assertTrue(locationList.contains(location2));


    }

    @Test
    public void updateLocation() {

        Location location = new Location();
        location.setAddress("California");
        location.setName("Location name");
        location.setLongitude((float) 0.55);
        location.setLatitude((float) 0.11);
        location = locationDao.addLocation(location);

        Location fromDao = locationDao.getLocationById(location.getId());
        assertEquals(location, fromDao);

        location.setName("Location update");
        locationDao.updateLocation(location);
        fromDao = locationDao.getLocationById(location.getId());
        assertEquals(location, fromDao);

    }

    @Test
    // Todo :
    public void deleteLocationById() {

        Location location = new Location();
        location.setAddress("California");
        location.setName("Location name");
        location.setLongitude((float) 0.55);
        location.setLatitude((float) 0.11);
        location = locationDao.addLocation(location);

        Location fromDao = locationDao.getLocationById(location.getId());
        assertEquals(location, fromDao);

        locationDao.deleteLocationById(fromDao.getId());
        fromDao = locationDao.getLocationById(fromDao.getId());
        assertNull(fromDao);

        // Todo: maybe cascade delete

    }
}