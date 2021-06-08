package com.sg.superhero.controller;

import com.sg.superhero.dao.HeroDao;
import com.sg.superhero.dao.LocationDao;
import com.sg.superhero.dao.OrganizationDao;
import com.sg.superhero.dao.SuperpowerDao;
import com.sg.superhero.model.Hero;
import com.sg.superhero.model.Superpower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.validation.*;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class HeroController {
    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    HeroDao heroDao;

    @Autowired
    SuperpowerDao superpowerDao;

    Set<ConstraintViolation<Hero>> violations = new HashSet<>();


    @GetMapping("heroes")
    public String displayHeroes(Model model) {
        List<Hero> heroes = heroDao.getAllHeroes();
        List<Superpower> supers = superpowerDao.getAllSuperpowers();
        model.addAttribute("superpowers",supers);
        model.addAttribute("heroes", heroes);
        model.addAttribute("errors",violations);
        return "heroes";
    }
    @PostMapping("addHero")
    public String addHero(@Valid Hero hero,BindingResult result, HttpServletRequest request) {
        String stringId = request.getParameter("superpowerId");
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();

        if(stringId == null){
            FieldError err = new FieldError("hero", "name", "Superpower cannot be blank");
            result.addError(err);
        }

        // Invalid superpower
        try{
            int superId = Integer.parseInt(stringId);
            hero.setSuperpower(superpowerDao.getSuperpowerById(superId));
        }catch (NumberFormatException e){}


        violations = validate.validate(hero);
        if(violations.isEmpty()){
            heroDao.addHero(hero);
        }
        return "redirect:/heroes";
    }
    // Done
    @GetMapping("deleteHero")
    public String deleteHero(Integer id) {
        heroDao.deleteHeroById(id);
        return "redirect:/heroes";
    }

    @GetMapping("editHero")
    public String editHero(Integer id, Model model){

        Hero hero = heroDao.getHeroById(id);
        hero.setSuperpower(superpowerDao.getSuperpowerById(hero.getSuperpower().getId()));
        List<Superpower> supers = superpowerDao.getAllSuperpowers();
        model.addAttribute("hero", hero);
        model.addAttribute("superpowers", supers);
        return "editHero";
    }


    @PostMapping("editHero")
    public String performEditHero(@Valid Hero hero, BindingResult result, HttpServletRequest request, Model model) {
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        String stringId = request.getParameter("superpowerId");

        hero.setSuperpower(superpowerDao.getSuperpowerById(Integer.parseInt(stringId)));

        if(result.hasErrors()) {
            List<Superpower> supers = superpowerDao.getAllSuperpowers();
            model.addAttribute("hero", hero);
            model.addAttribute("superpowers", supers);
            model.addAttribute("errors",violations);
            return "editHero";
        }

        heroDao.updateHero(hero);

        return "redirect:/heroes";
}

}
