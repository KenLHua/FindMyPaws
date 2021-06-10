package com.sg.findmypaws.dao;

import com.sg.findmypaws.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;

@Repository
public class AnimalDaoDB implements AnimalDao{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Animal addAnimal(Animal animal) {
        final String INSERT_ANIMAL = "INSERT INTO animals(name,nametag,species," +
                "breed, female, height, weight, age, status, hash, description, image, ownerId, date, color)" +
                " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?, ?,?)";
        jdbc.update(INSERT_ANIMAL,
                animal.getName(),
                animal.hasNameTag(),
                animal.getSpecies(),
                animal.getBreed(),
                animal.isFemale(),
                animal.getHeight(),
                animal.getWeight(),
                animal.getAge(),
                animal.getStatus(),
                animal.getHash(),
                animal.getDescription(),
                animal.getImage(),
                animal.getOwner().getId(),
                animal.getDate(),
                animal.getColor()
                );

        int newId =jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        animal.setId(newId);
        return animal;
    }

    @Override
    public Animal getAnimalById(int id) {
        final String SELECT_ANIMAL_BY_ID = "SELECT * FROM animals WHERE id = ?;";
        try{
            Animal animal = jdbc.queryForObject(SELECT_ANIMAL_BY_ID, new AnimalMapper(), id);
            return animal;
        }
        catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateAnimal(Animal animal) {
        final String UPDATE_ANIMAL = "UPDATE animals SET name = ?, nametag=?, species=?, breed=?, female=?, " +
                "height=?, weight=?, age=?, status=?, hash=?, description=?, image=?, ownerId=?, date=?, color=? WHERE id = ?";
        System.out.println("Update" + animal);
        jdbc.update(UPDATE_ANIMAL,
                animal.getName(), animal.hasNameTag(),
                animal.getSpecies(), animal.getBreed(),
                animal.isFemale(), animal.getHeight(),
                animal.getWeight(), animal.getAge(),
                animal.getStatus(), animal.getHash(),
                animal.getDescription(), animal.getImage(),
                animal.getOwner().getId(), animal.getDate(),
                animal.getColor(), animal.getId());
    }

    @Override
    public void deleteAnimalById(int id) {
        final String DELETE_ANIMAL_OWNER_FK = "DELETE FROM animals WHERE id = ?";
        final String DELETE_ANIMAL_BY_ID = "DELETE FROM animals WHERE id = ?";
        jdbc.update(DELETE_ANIMAL_BY_ID, id);

    }

    @Override
    public List<Animal> getAllAnimals() {
        final String GET_ALL_ANIMALS = "SELECT * FROM animals";
        return jdbc.query(GET_ALL_ANIMALS, new AnimalMapper());
    }

    public static final class AnimalMapper implements RowMapper<Animal> {

        @Override
        public Animal mapRow(ResultSet rs, int index) throws SQLException {
            Animal animal = new Animal();
            animal.setId(rs.getInt("id"));
            animal.setDate(rs.getDate("date").toLocalDate());
            animal.setStatus(rs.getInt("status"));
            animal.setName(rs.getString("name"));
            animal.setBreed(rs.getString("breed"));
            animal.setSpecies(rs.getString("species"));
            animal.setAge(rs.getInt("age"));
            animal.setColor(rs.getString("color"));
            animal.setDescription(rs.getString("description"));
            animal.setImage(rs.getString("image"));
            animal.setHash(rs.getString("hash"));
            animal.setHeight(rs.getInt("height"));
            animal.setWeight(rs.getInt("weight"));
            animal.setFemale(rs.getBoolean("female"));
            animal.setNameTag(rs.getBoolean("nameTag"));
            return animal;
        }
    }
}
