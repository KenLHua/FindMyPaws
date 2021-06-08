package com.sg.superhero.dao;

import com.sg.superhero.model.Hero;
import com.sg.superhero.model.Organization;

import java.util.List;

public interface OrganizationDao {
    Organization getOrganizationById(int id);
    List<Organization> getAllOrganization();
    Organization addOrganization(Organization organization);
    void updateOrganization(Organization organization);
    void deleteOrganizationById(int id);
    List<Organization> getOrgForHeroes(Hero hero);
}
