package com.sg.findmypaws.dao;

import com.sg.findmypaws.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
            final String SELECT_LOCATION_BY_ID = "SELECT * FROM locations WHERE id = ?";
            return jdbc.queryForObject(SELECT_LOCATION_BY_ID, new LocationDaoDB.LocationMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        final String SELECT_ALL_LOCATION = "SELECT * FROM locations";
        return jdbc.query(SELECT_ALL_LOCATION, new LocationDaoDB.LocationMapper());
    }

    @Override
    public List<Location> getAllLocationsWithFilter(Location filter, int radius) {
        final String SELECT_ALL_LOCATION_WITH_FILTER = "SELECT id, "
            + "( 3959 * acos " +
                "( cos ( radians(?) ) * cos( radians( latitude ) ) * cos( radians( longitude ) - radians(?) ) + sin ( radians(?) ) * sin( radians( latitude ) ))) AS distance"
            + "FROM locations"
            + "HAVING distance < ?"
            + "ORDER BY distance"
            + "LIMIT 0 , 20;";

        jdbc.update(SELECT_ALL_LOCATION_WITH_FILTER,
                filter.getLatitude(),
                filter.getLongitude(),
                filter.getLatitude(),
                radius);

        return jdbc.query(SELECT_ALL_LOCATION_WITH_FILTER, new LocationDaoDB.LocationMapper());
    }

    @Override
    @Transactional
    public Location addLocation(Location location) {
        final String INSERT_LOCATION = "INSERT INTO locations(name,latitude,longitude,?) "
                + "VALUES(?,?,?,?)";
        jdbc.update(INSERT_LOCATION,
                location.getName(),
                location.getLatitude(),
                location.getLongitude(),
                location.getAddress(),
                location.getDescription());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        location.setId(newId);
        return location;
    }

    @Override
    public void updateLocation(Location location) {
        final String UPDATE_LOCATION = "UPDATE locations SET Name = ?, address = ? , Description = ? , latitude = ? ,"
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
        final String DELETE_SIGHTINGS = "DELETE FROM sightings WHERE locationId = ?";
        jdbc.update(DELETE_SIGHTINGS, id);

        final String DELETE_LOCATION = "DELETE FROM locations WHERE id = ?";
        jdbc.update(DELETE_LOCATION, id);
    }

    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location location = new Location();
            location.setId(rs.getInt("id"));
            location.setName(rs.getString("Name"));
            location.setAddress(rs.getString("Address"));
            location.setDescription(rs.getString("Description"));
            location.setLatitude(rs.getFloat("Latitude"));
            location.setLongitude(rs.getFloat("Longitude"));

            return location;
        }
    }
}
