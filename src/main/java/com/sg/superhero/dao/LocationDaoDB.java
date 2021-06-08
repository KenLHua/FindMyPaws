package com.sg.superhero.dao;

import com.sg.superhero.model.Location;
import com.sg.superhero.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LocationDaoDB implements LocationDao{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Location getLocationById(int id) {
        try {
            final String SELECT_LOCATION_BY_ID = "SELECT * FROM location WHERE id = ?";
            return jdbc.queryForObject(SELECT_LOCATION_BY_ID, new LocationDaoDB.LocationMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        final String SELECT_ALL_LOCATION = "SELECT * FROM location";
        return jdbc.query(SELECT_ALL_LOCATION, new LocationDaoDB.LocationMapper());
    }

    @Override
    public Location addLocation(Location location) {
        final String INSERT_LOCATION = "INSERT INTO location(name,address,description,latitude,longitude) "
                + "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_LOCATION,
                location.getName(),
                location.getAddress(),
                location.getDescription(),
                location.getLatitude(),
                location.getLongitude());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        location.setId(newId);
        return location;
    }

    @Override
    public void updateLocation(Location location) {
        final String UPDATE_LOCATION = "UPDATE location SET Name = ?, address = ? , Description = ? , latitude = ? ,"
                +"longitude = ? "
                + "WHERE id = ?";
        jdbc.update(UPDATE_LOCATION,
                location.getName(),
                location.getAddress(),
                location.getDescription(),
                location.getLatitude(),
                location.getLongitude(),
                location.getId());
    }

    @Override
    public void deleteLocationById(int id) {
        final String DELETE_SIGHTINGS = "DELETE FROM sighting WHERE locationId = ?";
        jdbc.update(DELETE_SIGHTINGS, id);

        final String DELETE_LOCATION = "DELETE FROM location WHERE id = ?";
        jdbc.update(DELETE_LOCATION, id);
    }

    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location location = new Location();
            location.setId(rs.getInt("id"));
            location.setName(rs.getString("Name"));
            location.setDescription(rs.getString("Description"));
            location.setAddress(rs.getString("Address"));
            location.setLatitude(rs.getFloat("Latitude"));
            location.setLongitude(rs.getFloat("Longitude"));

            return location;
        }
    }
}
