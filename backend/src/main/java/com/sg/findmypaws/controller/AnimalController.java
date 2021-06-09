package com.sg.findmypaws.controller;

import com.sg.findmypaws.dao.AnimalDao;
import com.sg.findmypaws.dao.LocationDao;
import com.sg.findmypaws.dao.OwnerDao;
import com.sg.findmypaws.dao.SightingDao;
import com.sg.findmypaws.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@RestController
@RequestMapping("/api")
public class AnimalController {
    @Autowired
    AnimalDao animalDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    OwnerDao ownerDao;

    @Autowired
    SightingDao sightingDao;

    @GetMapping("animals")
    public String displayAnimals(Model model){
        // Todo: get all animals, pass it to model as attribute
        return null;
    }

    @PostMapping("addAnimal")
    public String addAnimal(Animal input, HttpServletRequest request){
        // Todo: create animal object
        Animal animal = new Animal();
        // Set all class fields
        return null;
    }

    @DeleteMapping("deleteAnimal")
    public String deleteAnimal(Integer id) throws SQLException {
        animalDao.deleteAnimalById(id);
        return "redirect:/animals";
    }

    @PutMapping("editAnimal")
    public String editAnimal(Animal input, HttpServletRequest request)throws SQLException{
        // Todo: create animal object
        Animal animal = new Animal();

        // Set all class fields
        animalDao.updateAnimal(animal);
        return null;
    }


}
