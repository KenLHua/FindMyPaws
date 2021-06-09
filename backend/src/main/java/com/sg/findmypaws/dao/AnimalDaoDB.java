package com.sg.findmypaws.dao;

import com.sg.findmypaws.model.Animal;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnimalDaoDB implements AnimalDao{
    @Override
    public Animal addAnimal(Animal animal) {
        return null;
    }

    @Override
    public Animal getAnimalById(int id) {
        return null;
    }

    @Override
    public void updateAnimal(Animal animal) {

    }

    @Override
    public void deleteAnimalById(int id) {

    }

    @Override
    public List<Animal> getAllAnimals() {
        return null;
    }

    @Override
    public List<Animal> getAllFilteredAnimals(Animal filter) {
        return null;
    }
}
