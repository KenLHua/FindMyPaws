package com.sg.superhero.model;

import java.util.List;
import java.util.Objects;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Sighting {
    private int id;
    private Hero hero;
    private Location location;
    private LocalDateTime dateTime;
    private String dateTimeP;

    public String getDateTimeP() {
        return dateTimeP;
    }

    public void setDateTimeP(String dateTimeP) {
        this.dateTimeP = dateTimeP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sighting sighting = (Sighting) o;
        return id == sighting.id && Objects.equals(hero, sighting.hero) && Objects.equals(location, sighting.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hero, location);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime;}

    public LocalDateTime getDateTime() {return dateTime;}

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
