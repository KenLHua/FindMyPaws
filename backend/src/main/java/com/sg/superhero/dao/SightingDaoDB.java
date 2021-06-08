package com.sg.superhero.dao;

import com.sg.superhero.dao.SightingDao;
import com.sg.superhero.model.*;
import com.sg.superhero.model.Sighting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class SightingDaoDB implements SightingDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Sighting getSightingById(int id) {
        try {
            final String SELECT_SIGHTING_BY_ID = "SELECT * FROM sighting WHERE id = ?";
            Sighting sighting = jdbc.queryForObject(SELECT_SIGHTING_BY_ID, new SightingDaoDB.SightingMapper(), id);
            sighting.setHero(getHeroForSighting(sighting.getId()));
            sighting.setLocation(getLocationForSighting(sighting.getId()));

            return sighting;

        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Hero getHeroForSighting(int id) {
        final String SELECT_HERO_FOR_SIGHTING = "SELECT h.* FROM hero h JOIN "
                + "sighting s ON h.Id = s.heroId WHERE s.Id = ?";
        Hero hero = jdbc.queryForObject(SELECT_HERO_FOR_SIGHTING, new HeroDaoDB.HeroMapper(), id);

        return hero;
    }

    @Override
    public Location getLocationForSighting(int id) {
        final String SELECT_LOCATION_FOR_SIGHTING = "SELECT l.* FROM location l JOIN "
                + "sighting s ON l.Id = s.locationId WHERE s.Id = ?";
        Location location = jdbc.queryForObject(SELECT_LOCATION_FOR_SIGHTING, new LocationDaoDB.LocationMapper(), id);
        return location;
    }

    @Override
    public List<Sighting> getAllSighting() {
        final String SELECT_ALL_SIGHTING = "SELECT * FROM sighting";
        List<Sighting> sightings = jdbc.query(SELECT_ALL_SIGHTING, new SightingDaoDB.SightingMapper());
        associateHeroes(sightings);
        associateLocations(sightings);

        return sightings;
    }

    private void associateHeroes(List<Sighting> sightings) {
        for (Sighting sighting : sightings) {
            sighting.setHero(getHeroForSighting(sighting.getId()));
        }
    }

    private void associateLocations(List<Sighting> sightings) {
        for (Sighting sighting : sightings) {
            sighting.setLocation(getLocationForSighting(sighting.getId()));
        }
    }

    @Override
    public Sighting addSighting(Sighting sighting) {
        final String INSERT_SIGHTING = "INSERT INTO sighting(heroId,locationId, dateTime) "
                + "VALUES(?,?, ?)";
        jdbc.update(INSERT_SIGHTING,
                sighting.getHero().getId(),
                sighting.getLocation().getId(),
                Timestamp.valueOf(sighting.getDateTime()));

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setId(newId);
        return sighting;
    }

    @Override
    public void updateSighting(Sighting sighting) {
        final String UPDATE_SIGHTING = "UPDATE sighting SET heroId = ?, locationId = ? , dateTime = ? "
                + "WHERE id = ?";
        jdbc.update(UPDATE_SIGHTING,
                sighting.getHero().getId(),
                sighting.getLocation().getId(),
                Timestamp.valueOf(sighting.getDateTime()),
                sighting.getId());
    }

    @Override
    public void deleteSightingById(int id) {
        final String DELETE_SIGHTING = "DELETE FROM sighting WHERE id = ?";
        jdbc.update(DELETE_SIGHTING, id);

    }

    public static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setId(rs.getInt("id"));
            sighting.setDateTime(rs.getTimestamp("dateTime").toLocalDateTime());

            return sighting;
        }
    }
}
