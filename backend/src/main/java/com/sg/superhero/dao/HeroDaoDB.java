package com.sg.superhero.dao;

import com.sg.superhero.model.Hero;
import com.sg.superhero.model.Superpower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class HeroDaoDB implements HeroDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Hero getHeroById(int id) {
        try {
            final String SELECT_HERO_BY_ID = "SELECT * FROM hero WHERE id = ?";
            Hero hero = jdbc.queryForObject(SELECT_HERO_BY_ID, new HeroDaoDB.HeroMapper(), id);
            hero.setSuperpower(getSuperpowerForHero(hero.getId()));
            return hero;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    public Superpower getSuperpowerForHero(int id){
        final String SELECT_SP_FROM_HERO = "SELECT * FROM superpower sp JOIN hero h ON h.superpowerId = sp.id WHERE h.id = ?";
        Superpower sp = jdbc.queryForObject(SELECT_SP_FROM_HERO, new SuperpowerDaoDB.SuperpowerMapper(), id);
        return sp;
    }

    @Override
    public List<Hero> getAllHeroes() {
        final String SELECT_ALL_HERO = "SELECT * FROM hero";
        List<Hero> heroes = jdbc.query(SELECT_ALL_HERO, new HeroDaoDB.HeroMapper());
        for(Hero hero: heroes){
            hero.setSuperpower(getSuperpowerForHero(hero.getId()));
        }
        return heroes;
    }

    @Override
    public Hero addHero(Hero hero) {
        final String INSERT_HERO = "INSERT INTO hero(name,superpowerId,description,evil) "
                + "VALUES(?,?,?,?)";
        jdbc.update(INSERT_HERO,
                hero.getName(),
                hero.getSuperpower().getId(),
                hero.getDescription(),
                hero.isEvil());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        hero.setId(newId);
        return hero;
    }

    @Override
    public void updateHero(Hero hero) {
        final String UPDATE_HERO = "UPDATE hero SET Name = ?, SuperpowerId = ? , Description = ? ,"
                +"evil = ? "
                + "WHERE id = ?";
        jdbc.update(UPDATE_HERO,
                hero.getName(),
                hero.getSuperpower().getId(),
                hero.getDescription(),
                hero.isEvil(),
                hero.getId());
    }

    @Override
    public void deleteHeroById(int id) {
        final String DELETE_SIGHTINGS = "DELETE FROM sighting WHERE heroId = ?";
        jdbc.update(DELETE_SIGHTINGS, id);

        final String DELETE_HERO_ORG = "DELETE FROM hero_organization WHERE heroId = ?";
        jdbc.update(DELETE_HERO_ORG, id);

        final String DELETE_HERO = "DELETE FROM hero WHERE id = ?";
        jdbc.update(DELETE_HERO, id);

    }

    public static final class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int index) throws SQLException {
            Hero hero = new Hero();
            hero.setId(rs.getInt("id"));
            hero.setName(rs.getString("Name"));
            hero.setEvil(rs.getBoolean("Evil"));
            hero.setDescription(rs.getString("Description"));

            return hero;
        }
    }
}
