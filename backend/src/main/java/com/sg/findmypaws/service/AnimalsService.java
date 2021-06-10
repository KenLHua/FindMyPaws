package com.sg.findmypaws.service;

import com.sg.findmypaws.dao.AnimalDao;
import com.sg.findmypaws.dao.OwnerDao;
import com.sg.findmypaws.dao.OwnerDaoDB;
import com.sg.findmypaws.dao.SightingDao;
import com.sg.findmypaws.model.Animal;
import com.sg.findmypaws.model.Owner;
import com.sg.findmypaws.model.Sighting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalsService {
    @Autowired
    AnimalDao animalDao;

    @Autowired
    SightingDao sightingDao;

    @Autowired
    OwnerDao ownerDao;

    @Autowired
    JdbcTemplate jdbc;

    // Todo: untested
    public List<Animal> filterAnimals(Animal filterAnimal, boolean useFemale, boolean useNameTag){
        List<Animal> animals = animalDao.getAllAnimals();
        List<Animal> animalsFiltered = animals.stream()
                .filter(curr -> compareAnimal(curr, filterAnimal))
                .collect(Collectors.toList());
        return animalsFiltered;
    }

    public boolean compareAnimal(Animal curr, Animal filterAnimal){
        boolean nameMatch = true;
        boolean speciesMatch = true;
        boolean breedMatch = true;
        boolean femaleMatch = true;
        boolean nameTagMatch = true;
        boolean colorMatch = true;
        boolean heightMatch = true;
        boolean weightMatch = true;
        boolean dateMatch = true;

        if(filterAnimal.getBreed() != null)
            breedMatch = curr.getBreed().equals(filterAnimal.getBreed());
        if(filterAnimal.getSpecies() != null)
            speciesMatch = curr.getSpecies().equals(filterAnimal.getSpecies());
        if(filterAnimal.isFemale() != null)
            femaleMatch = (curr.isFemale() == filterAnimal.isFemale());
        if(filterAnimal.hasNameTag() != null)
            nameTagMatch = (curr.hasNameTag() == filterAnimal.hasNameTag());
        if(filterAnimal.getName() != null && filterAnimal.hasNameTag() != null) {
            nameMatch = curr.getName().equals(filterAnimal.getName());
            nameMatch = ! nameTagMatch || nameMatch; // equivalent to if then
        }

        if(filterAnimal.getColor() != null)
            colorMatch = filterAnimal.getColor().equals(curr.getColor());
        if(filterAnimal.getHeight() > -1)
            heightMatch = filterAnimal.getHeight() == curr.getHeight();
        if(filterAnimal.getWeight() > -1)
            weightMatch = filterAnimal.getWeight() == curr.getWeight();
        if(filterAnimal.getDate() != null)
            dateMatch = curr.getDate().isAfter(filterAnimal.getDate());

        return (nameMatch && speciesMatch && breedMatch && femaleMatch && colorMatch && heightMatch && weightMatch && nameTagMatch && dateMatch);
    }

    public Animal getAnimalById(int id) throws SQLException{
        Animal animal = animalDao.getAnimalById(id);
        animal.setOwner(getOwnerForAnimal(animal));
        return animal;
    }

    public void deleteAnimalById(int id) throws SQLException{
        Animal animal = this.getAnimalById(id);
        List<Sighting> sightings = sightingDao.getAllSightingsByAnimalId(animal.getId());

        // Todo: may need to use service instead of serviceDao to delete
        for(Sighting sighting: sightings){
            try {
                sightingDao.deleteSightingById(sighting.getId());
            }catch(SQLException e){
                System.err.println("Could not delete "+sighting.getId());
            }
        }
        animalDao.deleteAnimalById(animal.getId());
        ownerDao.deleteOwnerById(getOwnerForAnimal(animal).getId());

    }


    public Owner getOwnerForAnimal(Animal animal){
        final String SELECT_OWNERID_FOR_ANIMAL = "SELECT owners.id FROM owners JOIN animals a ON a.ownerId = owners.id WHERE a.id = ?";
        int ownerId =jdbc.queryForObject(SELECT_OWNERID_FOR_ANIMAL, Integer.class, animal.getId());
        Owner owner = ownerDao.getOwnerById(ownerId);
        return owner;
    }
    public Animal addAnimal(Animal animal) throws SQLIntegrityConstraintViolationException, SQLException{
        Owner owner = animal.getOwner();
        // Make sure owner exists
        if(owner == null|| ownerDao.getOwnerById(owner.getId()) == null){
            throw new SQLIntegrityConstraintViolationException("Owner does not exist for Animal");
        }

        animal = animalDao.addAnimal(animal);
        try {
            return this.getAnimalById(animal.getId());
        }catch (SQLException e){
            throw new SQLException("Could not retrieve newly created animal");
        }



    }
    public List<Animal> getAllAnimals(){
        List<Animal> animals = animalDao.getAllAnimals();
        for(Animal an : animals){
            an.setOwner(this.getOwnerForAnimal(an));
        }
        return animals;
    }
}
