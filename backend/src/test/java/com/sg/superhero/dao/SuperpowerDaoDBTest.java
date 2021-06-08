package com.sg.superhero.dao;

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
public class SuperpowerDaoDBTest {

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    HeroDao heroDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    SuperpowerDao superpowerDao;

    @Before
    public void setup(){
        List<Hero> heroes = heroDao.getAllHeroes();
        for (Hero hero : heroes) {
            heroDao.deleteHeroById(hero.getId());
        }
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        for (Superpower sps: superpowers) {
            superpowerDao.deleteSuperpowerById(sps.getId());
        }
    }

    @Test
    public void testAddAndGetSuperpower() {
        Superpower superpower = new Superpower();
        superpower.setName("Super Strength");
        superpower = superpowerDao.addSuperpower(superpower);

        Superpower fromDao = superpowerDao.getSuperpowerById(superpower.getId());
        assertEquals(superpower, fromDao);
    }

    @Test
    public void getAllSuperpowers() {
        Superpower superpower1 = new Superpower();
        superpower1.setName("Super Strength");
        superpower1 = superpowerDao.addSuperpower(superpower1);

        Superpower superpower2 = new Superpower();
        superpower2.setName("Super Fragility");
        superpower2 = superpowerDao.addSuperpower(superpower2);
        List<Superpower> supers = superpowerDao.getAllSuperpowers();
        assertTrue(supers.size() == 2);
        assertTrue(supers.contains(superpower1));
        assertTrue(supers.contains(superpower2));
    }


    @Test
    public void updateSuperpower() {
        Superpower superpower = new Superpower();
        superpower.setName("Super Strength");
        superpower = superpowerDao.addSuperpower(superpower);

        Superpower fromDao = superpowerDao.getSuperpowerById(superpower.getId());
        assertEquals(superpower, fromDao);

        superpower.setName("Super Fragility");
        superpowerDao.updateSuperpower(superpower);
        assertNotEquals(superpowerDao.getSuperpowerById(superpower.getId()), fromDao);
        assertEquals(superpower,superpowerDao.getSuperpowerById(superpower.getId()));



    }

    @Test
    public void deleteSuperpowerById() {
        Superpower superpower = new Superpower();
        superpower.setName("Super Strength");
        superpower = superpowerDao.addSuperpower(superpower);

        superpowerDao.deleteSuperpowerById(superpower.getId());
        assertNull(superpowerDao.getSuperpowerById(superpower.getId()));

        // Testing cascade delete
        superpower = new Superpower();
        superpower.setName("Super Weak");
        superpower = superpowerDao.addSuperpower(superpower);

        Hero hero = new Hero();
        hero.setName("Kenneth Hua");
        hero.setDescription("Me");
        hero.setEvil(true);
        hero.setSuperpower(superpower);
        heroDao.addHero(hero);

        superpowerDao.deleteSuperpowerById(superpower.getId());
        assertNull(superpowerDao.getSuperpowerById(superpower.getId()));
        assertNull(heroDao.getHeroById(hero.getId()));




    }
}