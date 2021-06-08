package com.sg.superhero.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Hero {


    private int id;
    @NotBlank(message="Name must not be blank")
    @Size(max = 30, message="Name must be less than 30 characters.")
    private String name;
    @Size(max = 50, message="Desc must be less than 50 characters.")
    private String description;
    @NotNull(message="Superpower null")
    private Superpower superpower;
    private boolean evil;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return id == hero.id && evil == hero.evil && name.equals(hero.name) && Objects.equals(description, hero.description) && superpower.equals(hero.superpower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, superpower, evil);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Superpower getSuperpower() {
        return superpower;
    }

    public void setSuperpower(Superpower superpower) {
        this.superpower = superpower;
    }

    public boolean isEvil() {
        return evil;
    }

    public void setEvil(boolean evil) {
        this.evil = evil;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", superpower=" + superpower +
                ", evil=" + evil +
                '}';
    }
}
