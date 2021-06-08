package com.sg.superhero.controller;

import com.sg.classroster.model.Teacher;
import com.sg.superhero.dao.HeroDao;
import com.sg.superhero.dao.LocationDao;
import com.sg.superhero.dao.OrganizationDao;
import com.sg.superhero.dao.SightingDao;
import com.sg.superhero.model.Hero;
import com.sg.superhero.model.Location;
import com.sg.superhero.model.Sighting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;

@Controller
public class SightingController {
    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    HeroDao heroDao;

    @Autowired
    SightingDao sightingDao;


    @GetMapping("")
    public String displayIndexSightings(Model model) {
        List<Sighting> sightings = sightingDao.getAllSighting();
//        Collections.reverse(sightings);

        for(Sighting sighting : sightings) {
            LocalDateTime dateTime = sighting.getDateTime();
            String timeToFormat = dateTime.toString();
            String[] result = timeToFormat.split("T");
            String timeToPrint = result[0] + ' ' + result[1];
            sighting.setDateTimeP(timeToPrint);
        }

        sightings.sort(Comparator.comparing(Sighting::getDateTimeP).reversed());   //sort by time

        if(sightings.size() > 10){
            sightings = sightings.subList(0,10);    //10 latest sightings
        }
        model.addAttribute("sightings", sightings);
        return "index";
    }


    @GetMapping("sightings")
    public String displaySightings(Model model) {
        List<Sighting> sightings = sightingDao.getAllSighting();
        List<Hero> heroes = heroDao.getAllHeroes();
        List<Location> locations = locationDao.getAllLocations();

        for(Sighting sighting : sightings) {
            LocalDateTime dateTime = sighting.getDateTime();
            String timeToFormat = dateTime.toString();
            String[] result = timeToFormat.split("T");
            String timeToPrint = result[0] + ' ' + result[1];
            sighting.setDateTimeP(timeToPrint);
        }

        model.addAttribute("sightings", sightings);
        model.addAttribute("heroes", heroes);
        model.addAttribute("locations",locations);
        return "sightings";
    }



    @PostMapping("addSighting")
    public String addSighting(HttpServletRequest request) {
        String heroId = request.getParameter("heroId");
        String locationId = request.getParameter("locationId");

        String time = request.getParameter("dateTime");

        String[] result = time.split("T");
        String timeToFormat = result[0] + ' ' + result[1];
        System.out.println(timeToFormat);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(timeToFormat,formatter);
        System.out.println(localDateTime);

        Sighting sighting = new Sighting();
        sighting.setHero(heroDao.getHeroById(Integer.parseInt(heroId)));
        sighting.setLocation(locationDao.getLocationById(Integer.parseInt(locationId)));
        sighting.setDateTime(localDateTime);
        sighting.setDateTimeP(timeToFormat);
        System.out.println(sighting.getDateTimeP());
        sightingDao.addSighting(sighting);
        return "redirect:/sightings";
    }
    // Done
    @GetMapping("deleteSighting")
    public String deleteStudent(Integer id) {
        sightingDao.deleteSightingById(id);
        return "redirect:/sightings";
    }

    @GetMapping("editSighting")
    public String editSighting(Integer id, Model model){
        Sighting sighting = sightingDao.getSightingById(id);
        List<Hero> heroes = heroDao.getAllHeroes();
        List<Location> locations = locationDao.getAllLocations();

        LocalDateTime dateTime = sighting.getDateTime();
        String timeToFormat = dateTime.toString();
        String[] result = timeToFormat.split("T");
        String timeToPrint = result[0] + ' ' + result[1];
        sighting.setDateTimeP(timeToPrint);

        model.addAttribute("sighting", sighting);
        model.addAttribute("heroes", heroes);
        model.addAttribute("locations",locations);
        return "editSighting";
    }


    @PostMapping("editSighting")
    public String performEditSighting(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Sighting sighting = sightingDao.getSightingById(id);

        int heroId = Integer.parseInt(request.getParameter("heroId"));
        Hero hero = heroDao.getHeroById(heroId);

        int locationId = Integer.parseInt(request.getParameter("locationId"));
        Location location = locationDao.getLocationById(locationId);

        String time = request.getParameter("dateTime");
        String[] result = time.split("T");
        String timeToFormat = result[0] + ' ' + result[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(timeToFormat,formatter);


        sighting.setDateTime(localDateTime);
        sighting.setHero(hero);
        sighting.setLocation(location);

        sightingDao.updateSighting(sighting);

        return "redirect:/sightings";
    }
}
