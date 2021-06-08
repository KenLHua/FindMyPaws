package com.sg.findmypaws.dao;

import com.sg.findmypaws.model.Animal;

import java.util.List;

public interface  AnimalDao {
    public Animal addAnimal(Animal animal);
    public Animal getAnimalById(int id);
    public Animal updateAnimal(Animal animal);
    public void deleteAnimalById(int id);
    public List<Animal> getAllAnimals();
    public List<Animal> getAllFilteredAnimals(Animal filter);

}
