package com.sg.superhero.controller;

import com.sg.superhero.dao.HeroDao;
import com.sg.superhero.dao.LocationDao;
import com.sg.superhero.dao.OrganizationDao;
import com.sg.superhero.model.Hero;
import com.sg.superhero.model.Location;
import com.sg.superhero.model.Organization;
import com.sg.superhero.model.OrganizationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;

@Controller
public class OrganizationController {
    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    HeroDao heroDao;

    Set<ConstraintViolation<Organization>> violations = new HashSet<>();
    List<Exception> errors = new ArrayList<>();

    // Todo :
    @GetMapping("organizations")
    public String displayOrganizations(Model model) {
        List<Organization> organizations = organizationDao.getAllOrganization();
        List<Hero> heroes = heroDao.getAllHeroes();

        model.addAttribute("organizations", organizations);
        model.addAttribute("heroes", heroes);
        model.addAttribute("errors", errors);
        return "organizations";
    }
    // Todo :
    @PostMapping("addOrganization")
    public String addOrganization(Organization organization, HttpServletRequest request) {
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(organization);

        String[] heroIds = request.getParameterValues("heroId");
        errors = new ArrayList<>();
        for (ConstraintViolation<Organization> err: violations) {
            errors.add(new OrganizationError(err.getMessage()));
        }

        if(heroIds == null){
            errors.add(new OrganizationError("Please select a hero"));
        }

        if(errors.size() == 0) {
            List<Hero> heroes = new ArrayList<>();
            for (String heroId : heroIds) {
                heroes.add(heroDao.getHeroById(Integer.parseInt(heroId)));
            }
            organization.setHeroes(heroes);
            organizationDao.addOrganization(organization);
        }
        return "redirect:/organizations";



    }
    // Done
    @GetMapping("deleteOrganization")
    public String deleteOrganization(Integer id) {
        organizationDao.deleteOrganizationById(id);
        return "redirect:/organizations";
    }
    // Todo:
    @GetMapping("editOrganization")
    public String editOrganization(Integer id, Model model) {
        Organization organization = organizationDao.getOrganizationById(id);
        List<Hero> heroes = heroDao.getAllHeroes();
        model.addAttribute("heroes", heroes);
        model.addAttribute("organization", organization);
        return "editOrganization";
    }

    // Todo:
    @PostMapping("editOrganization")
    public String performEditOrganization(@Valid Organization organization, BindingResult result, HttpServletRequest request, Model model) {
        String[] heroIds = request.getParameterValues("heroId");
        List<Hero> heroes = new ArrayList<>();
        if(heroIds != null){
            for(String heroId : heroIds){
                heroes.add(heroDao.getHeroById(Integer.parseInt(heroId)));
            }
        } else {
            FieldError error = new FieldError("organization", "heroes", "Must include one hero");
            result.addError(error);
        }
        organization.setHeroes(heroes);

        if(result.hasErrors()) {
            model.addAttribute("heroes", heroDao.getAllHeroes());
            model.addAttribute("organization", organization);
            return "editOrganization";
        }
        organizationDao.updateOrganization(organization);
        return "redirect:/organizations";
    }

    @GetMapping("organizationDetail")
    public String organizationDetail(Integer id, Model model) {
        Organization organization = organizationDao.getOrganizationById(id);
        model.addAttribute("organization", organization);
        return "organizationDetail";
    }


}
