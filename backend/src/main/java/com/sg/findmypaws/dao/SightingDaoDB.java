package com.sg.findmypaws.dao;

import com.sg.findmypaws.model.Animal;
import com.sg.findmypaws.model.Location;
import com.sg.findmypaws.model.Sighting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SightingDaoDB implements SightingDao{

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    LocationDaoDB locationDaoDB;

    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet resultSet, int i) throws SQLException {
            Sighting sighting = new Sighting();

            sighting.setId(resultSet.getInt("id"));
            sighting.setLocationId(resultSet.getInt("locationId"));
            sighting.setAnimalId(resultSet.getInt("personId"));
            sighting.setDate(resultSet.getDate("date"));

            return sighting;
        }
    }

    @Override
    public Sighting addSighting(Sighting sighting) {
        final String INSERT_SIGHTING = "INSERT INTO sightings(locationId, animalId, date) VALUES(?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        // used to transactionally get id of inserted row
        jdbc.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(INSERT_SIGHTING, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, sighting.getLocationId());
            statement.setInt(2, sighting.getAnimalId());
            statement.setDate(3, (Date) sighting.getDate());
            return statement;

        }, keyHolder);
        sighting.setId(keyHolder.getKey().intValue());

        return sighting;
    }

    // no incorrect id expected, should throw exception and not suppress with null
    @Override
    public Sighting getSightingById(int id) throws SQLException {
        Sighting sighting;

        final String GET_SIGHTING = "SELECT * FROM sightings WHERE id = ?;";
        try {
            sighting = jdbc.queryForObject(GET_SIGHTING,
                    new SightingMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            ex.printStackTrace();
            throw new SQLException("There exists no sighting by id: " + id, ex);
        }

        return sighting;
    }

    // no silent fail
    @Override
    public void updateSighting(Sighting sighting) throws SQLException {
        final String UPDATE_SIGHTING = "UPDATE sightings SET locationId = ?, animalId = ?, " +
                "date = ? WHERE id = ?;";
        boolean valid = 0 < jdbc.update(UPDATE_SIGHTING, sighting.getLocationId(), sighting.getAnimalId(),
                sighting.getDate(), sighting.getId());
        if (!valid) {
            throw new SQLException("There exists no sighting by id: " + sighting.getId());
        }
    }

    // no incorrect id expected, should throw exception and not suppress
    @Override
    public void deleteSightingById(int id) throws SQLException {
        final String sql = "DELETE FROM sightings WHERE id = ?;";
        boolean valid = jdbc.update(sql, id) > 0;
        if (!valid) {
            throw new SQLException("There exists no sighting by id: " + id);
        }
    }

    @Override
    public List<Sighting> getAllSightings() {
        final String GET_SIGHTINGS = "SELECT * FROM sightings ORDER BY date DESC;";
        List<Sighting> sightings = jdbc.query(GET_SIGHTINGS, new SightingMapper());
        return sightings;
    }

    @Override
    public List<Sighting> getAllSightingsByLocId(int locId) {
        final String GET_SIGHTINGS = "SELECT * FROM sightings WHERE locationId = ? ORDER BY date DESC;";
        List<Sighting> sightings = jdbc.query(GET_SIGHTINGS, new SightingMapper(), locId);
        return sightings;
    }

    @Override
    public List<Sighting> getAllSightingsByAnimalId(int animalId) {
        final String GET_SIGHTINGS = "SELECT * FROM sightings WHERE animalId = ? ORDER BY date DESC;";
        List<Sighting> sightings = jdbc.query(GET_SIGHTINGS, new SightingMapper(), animalId);
        return sightings;
    }
}
