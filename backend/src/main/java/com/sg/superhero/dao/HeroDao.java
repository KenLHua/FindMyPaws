package com.sg.superhero.dao;

import com.sg.superhero.model.Hero;
import com.sg.superhero.model.Superpower;

import java.util.List;

public interface HeroDao {
    Hero getHeroById(int id);
    List<Hero> getAllHeroes();
    Hero addHero(Hero hero);
    void updateHero(Hero hero);
    void deleteHeroById(int id);
    Superpower getSuperpowerForHero(int id);
}
