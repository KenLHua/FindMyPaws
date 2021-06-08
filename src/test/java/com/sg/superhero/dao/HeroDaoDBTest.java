package com.sg.superhero.dao;

import com.sg.classroster.model.Course;
import com.sg.classroster.model.Student;
import com.sg.classroster.model.Teacher;
import com.sg.superhero.model.Hero;
import com.sg.superhero.model.Superpower;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HeroDaoDBTest {
    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    HeroDao heroDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    SuperpowerDao superpowerDao;

    Superpower superpower;

    @Before
    public void setUp() {
        List<Hero> heroes = heroDao.getAllHeroes();
        for (Hero hero : heroes) {
            heroDao.deleteHeroById(hero.getId());
        }
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        for (Superpower sps: superpowers) {
            superpowerDao.deleteSuperpowerById(sps.getId());
        }

        superpower = new Superpower();
        superpower.setName("Super Strength");
        superpowerDao.addSuperpower(superpower);
    }

    @Test
    public void testAddAndGetHeroById() {


        Hero hero = new Hero();
        hero.setName("Test Hero");
        hero.setSuperpower(superpower);
        hero.setEvil(false);
        hero = heroDao.addHero(hero);

        Hero fromDao = heroDao.getHeroById(hero.getId());
        assertEquals(hero, fromDao);
    }

    @Test
    public void getAllHeroes() {
        Hero hero = new Hero();
        hero.setName("Test Hero1");
        hero.setSuperpower(superpower);
        hero.setEvil(false);
        hero = heroDao.addHero(hero);

        Hero hero2 = new Hero();
        hero2.setName("Test Hero 2");
        hero2.setSuperpower(superpower);
        hero2.setEvil(false);
        hero2 = heroDao.addHero(hero2);

        List<Hero> heroList = heroDao.getAllHeroes();

        assertEquals(2, heroList.size());
        assertTrue(heroList.contains(hero));
        assertTrue(heroList.contains(hero2));
    }


    @Test
    public void updateHero() {
        Hero hero = new Hero();
        hero.setName("Test Hero1");
        hero.setSuperpower(superpower);
        hero.setEvil(false);
        hero = heroDao.addHero(hero);

        Hero fromDao = heroDao.getHeroById(hero.getId());
        assertEquals(hero,fromDao);

        hero.setEvil(true);
        heroDao.updateHero(hero);

        fromDao = heroDao.getHeroById(hero.getId());
        assertEquals(hero, fromDao);
    }

    @Test
    // Todo:
    public void testDeleteHeroById() {
        Hero hero = new Hero();
        hero.setName("Test Hero1");
        hero.setSuperpower(superpower);
        hero.setEvil(false);
        hero = heroDao.addHero(hero);

        Hero fromDao = heroDao.getHeroById(hero.getId());
        assertEquals(hero,fromDao);

        heroDao.deleteHeroById(fromDao.getId());

        fromDao = heroDao.getHeroById(fromDao.getId());

        assertNull(fromDao);

        // Todo: maybe add do cascade delete for location


    }
}