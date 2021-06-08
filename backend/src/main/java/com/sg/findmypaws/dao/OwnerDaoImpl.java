package com.sg.findmypaws.dao;


import com.sg.findmypaws.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OwnerDaoImpl implements OwnerDao{

    @Autowired
    JdbcTemplate jdbc;


    @Override
    @Transactional
    public Owner addOwner(Owner owner) {
        final String INSERT_OWNER = "INSERT INTO owners(name,phone, email) VALUES(?,?,?)";
        jdbc.update(INSERT_OWNER,
                owner.getName(),
                owner.getPhone(),
                owner.getEmail());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        owner.setId(newId);
        return owner;
    }

    @Override
    public Owner getOwnerById(int id) {

        final String SELECT_OWNER_BY_ID = "SELECT * FROM owners WHERE id = ?";

        return jdbc.queryForObject(SELECT_OWNER_BY_ID, new OwnerMapper(), id);
    }

    @Override
    @Transactional
    public Owner updateOwner(Owner owner) {
        final String UPDATE_OWNER = "UPDATE owner SET name =?, phone = ?, email =? WHERE id = ?";
        jdbc.update(UPDATE_OWNER,
                owner.getName(),
                owner.getPhone(),
                owner.getEmail(),
                owner.getId());

        Owner fromDao = getOwnerById(owner.getId());

        return fromDao;
    }

    @Override
    @Transactional
    public Owner deleteOwnerById(int id) {
        final String DELETE_SIGHTINGS_BY_OWNER = "DELETE sightings.* FROM sightings " +
                "JOIN animals ON sightings.animalId = animals.id WHERE animals.ownerId = ?";

        jdbc.update(DELETE_SIGHTINGS_BY_OWNER, id);

        final String DELETE_ANIMAL_BY_OWNER = "DELETE animals* FROM animals WHERE ownerId = ?";
        jdbc.update(DELETE_ANIMAL_BY_OWNER, id);

        Owner fromDao = getOwnerById(id);

        final String DELETE_OWNER = "DELETE FROM owners WHERE id = ?";
        jdbc.update(DELETE_OWNER, id);

        return fromDao;
    }

    @Override
    public List<Owner> getAllOwners() {
        final String SELECT_ALL_OWNERS = "SELECT * FROM owners";
        return jdbc.query(SELECT_ALL_OWNERS, new OwnerMapper());
    }

    @Override
    public List<Owner> getAllFilteredOwners(Owner filter) {
        final String SELECT_ALL_FILTER_OWNERS = "SELECT * FROM owners WHERE name = ?";
        return jdbc.query(SELECT_ALL_FILTER_OWNERS, new OwnerMapper(), filter.getName());
    }

    public static final class OwnerMapper implements RowMapper<Owner> {
        @Override
        public Owner mapRow(ResultSet rs, int index) throws SQLException {
            Owner owner = new Owner();
            owner.setId(rs.getInt("id"));
            owner.setName(rs.getString("name"));
            owner.setPhone(rs.getString("phone"));
            owner.setEmail(rs.getString("email"));
            return owner;
        }
    }
}
