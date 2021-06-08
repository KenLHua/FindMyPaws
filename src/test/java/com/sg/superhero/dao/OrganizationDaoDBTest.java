package com.sg.superhero.dao;

import com.sg.superhero.model.Hero;
import com.sg.superhero.model.Location;
import com.sg.superhero.model.Organization;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationDaoDBTest {

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    HeroDao heroDao;

    @Autowired
    LocationDao locationDao;

    @Before
    public void setUp() {
        List<Hero> heroes = heroDao.getAllHeroes();
        for (Hero hero : heroes) {
            heroDao.deleteHeroById(hero.getId());
        }

        List<Organization> organizations = organizationDao.getAllOrganization();
        for (Organization organization : organizations) {
            organizationDao.deleteOrganizationById(organization.getId());
        }
    }

    @Test
    public void testGetHeroes() {


    }

//    @Test
//    public void testAddAndGetOrganizationById() {
//
//        Hero hero = new Hero();
//        hero.setName("Test Hero1");
//        hero.setSuperPower("Superstrength");
//        hero.setEvil(false);
//        hero = heroDao.addHero(hero);
//
//
//        Organization organization = new Organization();
//
//        organization.setName("Organization name");
//        organization.setContactInfo("Contact info");
//        List<Hero> heroList = new ArrayList<Hero>();
//        heroList.add(hero);
//
//        organization.setHeroes(heroList);
//        organization = organizationDao.addOrganization(organization);
//
//        Organization fromDao = organizationDao.getOrganizationById(organization.getId());
//        assertEquals(organization, fromDao);
//    }
//
//    @Test
//    public void getAllOrganization() {
//
//        Hero hero = new Hero();
//        hero.setName("Test Hero1");
//        hero.setSuperPower("Superstrength");
//        hero.setEvil(false);
//        hero = heroDao.addHero(hero);
//
//
//        Organization organization = new Organization();
//
//        organization.setName("Organization name");
//        organization.setContactInfo("Contact info");
//        List<Hero> heroList = new ArrayList<Hero>();
//        heroList.add(hero);
//
//        organization.setHeroes(heroList);
//        organization = organizationDao.addOrganization(organization);
//
//        Organization fromDao = organizationDao.getOrganizationById(organization.getId());
//        assertEquals(organization, fromDao);
//
//        Hero hero2 = new Hero();
//        hero2.setName("Test Hero2");
//        hero2.setSuperPower("Superstrength");
//        hero2.setEvil(false);
//        hero2 = heroDao.addHero(hero2);
//
//
//        Organization organization2 = new Organization();
//
//        organization2.setName("Organization name");
//        organization2.setContactInfo("Contact info");
//        heroList = new ArrayList<Hero>();
//        heroList.add(hero2);
//
//        organization2.setHeroes(heroList);
//        organization2 = organizationDao.addOrganization(organization2);
//
//        fromDao = organizationDao.getOrganizationById(organization2.getId());
//        assertEquals(organization2, fromDao);
//
//        List<Organization> orgList = organizationDao.getAllOrganization();
//        assertEquals(2, orgList.size());
//
//        assertTrue(orgList.contains(organization));
//        assertTrue(orgList.contains(organization2));
//
//    }
//
//    @Test
//    public void updateOrganization() {
//        Hero hero = new Hero();
//        hero.setName("Test Hero1");
//        hero.setSuperPower("Superstrength");
//        hero.setEvil(false);
//        hero = heroDao.addHero(hero);
//
//        Organization organization = new Organization();
//
//        organization.setName("Organization name");
//        organization.setContactInfo("Contact info");
//        List<Hero> heroList = new ArrayList<Hero>();
//        heroList.add(hero);
//
//        organization.setHeroes(heroList);
//        organization = organizationDao.addOrganization(organization);
//
//        Organization fromDao = organizationDao.getOrganizationById(organization.getId());
//        assertEquals(organization, fromDao);
//
//        organization.setName("Organization edit name");
//        organizationDao.updateOrganization(organization);
//
//        fromDao = organizationDao.getOrganizationById(organization.getId());
//        assertEquals(organization, fromDao);
//
//
//    }
//
//    @Test
//    public void deleteOrganizationById() {
//        Hero hero = new Hero();
//        hero.setName("Test Hero1");
//        hero.setSuperPower("Superstrength");
//        hero.setEvil(false);
//        hero = heroDao.addHero(hero);
//
//        Organization organization = new Organization();
//
//        organization.setName("Organization name");
//        organization.setContactInfo("Contact info");
//        List<Hero> heroList = new ArrayList<Hero>();
//        heroList.add(hero);
//
//        organization.setHeroes(heroList);
//        organization = organizationDao.addOrganization(organization);
//        Organization fromDao = organizationDao.getOrganizationById(organization.getId());
//        assertEquals(organization, fromDao);
//
//        organizationDao.deleteOrganizationById(organization.getId());
//
//        fromDao = organizationDao.getOrganizationById(organization.getId());
//        assertNull(fromDao);
//
//    }

}