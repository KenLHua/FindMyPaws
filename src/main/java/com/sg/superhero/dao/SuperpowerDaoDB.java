package com.sg.superhero.dao;

import com.sg.superhero.model.Hero;
import com.sg.superhero.model.Superpower;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SuperpowerDaoDB implements SuperpowerDao {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    HeroDao heroDao;

    @Override
    public Superpower getSuperpowerById(int id) {
        try {
            final String SELECT_SP_BY_ID = "SELECT * FROM superpower WHERE id = ?";
            return jdbc.queryForObject(SELECT_SP_BY_ID, new SuperpowerDaoDB.SuperpowerMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Superpower> getAllSuperpowers() {
        final String SELECT_ALL_SP = "SELECT * FROM superpower";
        return jdbc.query(SELECT_ALL_SP, new SuperpowerDaoDB.SuperpowerMapper());
    }

    @Override
    public Superpower addSuperpower(Superpower sp) {
        final String INSERT_SP = "INSERT INTO superpower(name) "
                + "VALUES(?)";
        jdbc.update(INSERT_SP, sp.getName());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sp.setId(newId);
        return sp;
    }

    @Override
    public void updateSuperpower(Superpower sp) {
        final String UPDATE_SP = "UPDATE superpower SET Name = ? " + "WHERE id = ?";
        jdbc.update(UPDATE_SP, sp.getName(), sp.getId());

    }

    @Override
    public void deleteSuperpowerById(int id) {

        // Todo: change hero to take in a superheroid
        final String GET_ALL_HEROS_BY_SP = "SELECT * FROM hero WHERE superpowerId = ?";
        List<Hero> heroes = jdbc.query(GET_ALL_HEROS_BY_SP, new HeroDaoDB.HeroMapper(), id);

        for(Hero hero : heroes) {
            heroDao.deleteHeroById(hero.getId());
        }

        final String DELETE_SP = "DELETE FROM superpower WHERE id = ?";
        jdbc.update(DELETE_SP, id);

    }
    public static final class SuperpowerMapper implements RowMapper<Superpower> {

        @Override
        public Superpower mapRow(ResultSet rs, int index) throws SQLException {
            Superpower superPower = new Superpower();
            superPower.setId(rs.getInt("id"));
            superPower.setName(rs.getString("Name"));

            return superPower;
        }
    }
}
