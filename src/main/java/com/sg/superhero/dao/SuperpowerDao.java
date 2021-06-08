package com.sg.superhero.dao;

import com.sg.superhero.model.Superpower;

import java.util.List;

public interface SuperpowerDao {
    Superpower getSuperpowerById(int id);
    List<Superpower> getAllSuperpowers();
    Superpower addSuperpower(Superpower Superpower);
    void updateSuperpower(Superpower Superpower);
    void deleteSuperpowerById(int id);
}
