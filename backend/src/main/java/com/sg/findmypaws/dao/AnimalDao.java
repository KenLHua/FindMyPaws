package com.sg.findmypaws.dao;

import com.sg.findmypaws.model.Animal;

import java.sql.SQLException;
import java.util.List;

public interface  AnimalDao {
    public Animal addAnimal(Animal animal);
    public Animal getAnimalById(int id) throws SQLException;
    public void updateAnimal(Animal animal) throws SQLException;
    public void deleteAnimalById(int id) throws SQLException;
    public List<Animal> getAllAnimals();

}
