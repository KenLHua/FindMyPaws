package com.sg.superhero.controller;

import com.sg.superhero.dao.*;
import com.sg.superhero.model.Superpower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class SuperpowerController {
    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    HeroDao heroDao;

    @Autowired
    SightingDao sightingDao;

    @Autowired
    SuperpowerDao superPowerDao;

    @GetMapping("superpowers")
    public String displaySuperpowers(Model model) {
        List<Superpower> superpowers = superPowerDao.getAllSuperpowers();
        model.addAttribute("superpowers", superpowers);
        return "superpowers";
    }



    @PostMapping("addSuperpower")
    public String addSuperpower(@Valid Superpower superPower, HttpServletRequest request, BindingResult result) {
        // Todo: validation

        if(result.hasErrors()){
            return "redirect:/superpowers";
        }
        superPowerDao.addSuperpower(superPower);
        return "redirect:/superpowers";
    }

    @GetMapping("deleteSuperpower")
    public String deleteStudent(Integer id) {
        superPowerDao.deleteSuperpowerById(id);
        return "redirect:/superpowers";
    }

    @GetMapping("editSuperpower")
    public String editSuperpower(Integer id, Model model){
        Superpower sp = superPowerDao.getSuperpowerById(id);
        model.addAttribute("superpower", sp);
        return "editSuperpower";
    }


    @PostMapping("editSuperpower")
    public String performEditSuperpower(@Valid Superpower superPower, BindingResult result) {
        if(result.hasErrors()){
            return "redirect:/superpowers";
        }
        superPowerDao.updateSuperpower(superPower);

        return "redirect:/superpowers";
    }


}
