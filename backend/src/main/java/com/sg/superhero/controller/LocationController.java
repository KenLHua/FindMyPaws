package com.sg.superhero.controller;

import com.sg.superhero.dao.HeroDao;
import com.sg.superhero.dao.LocationDao;
import com.sg.superhero.dao.OrganizationDao;
import com.sg.superhero.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class LocationController {
    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    HeroDao heroDao;

    Set<ConstraintViolation<Location>> violations = new HashSet<>();


    @GetMapping("locations")
    public String displayLocations(Model model) {
        List<Location> locations = locationDao.getAllLocations();
        model.addAttribute("locations", locations);
        model.addAttribute("errors", violations);
        return "locations";
    }

    @PostMapping("addLocation")
    public String addLocation(@Valid Location location, HttpServletRequest request, BindingResult result) {

        if(result.hasErrors()){
            return "redirect:/locations";
        }

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(location);

        if(violations.isEmpty()) {
            locationDao.addLocation(location);
        }
        return "redirect:/locations";
    }
    // Done
    @GetMapping("deleteLocation")
    public String deleteLocation(Integer id) {
        locationDao.deleteLocationById(id);
        return "redirect:/locations";
    }
    // Done
    @GetMapping("editLocation")
    public String editLocation(Integer id, Model model) {
        Location location = locationDao.getLocationById(id);
        model.addAttribute("location", location);
        return "editLocation";
    }

    // Done
    @PostMapping("editLocation")
    public String performEditLocation(@Valid Location location, BindingResult result) {
        if(result.hasErrors()) {
            return "editLocation";
        }
        locationDao.updateLocation(location);
        return "redirect:/locations";
    }
}
