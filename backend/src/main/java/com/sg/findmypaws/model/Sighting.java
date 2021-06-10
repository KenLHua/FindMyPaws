package com.sg.findmypaws.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Sighting {
    public int id;
//    public Location location;
//    public Animal animal;
    public int locationId;
    public int animalId;
    public LocalDate date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sighting sighting = (Sighting) o;
        return id == sighting.id && locationId == sighting.locationId && animalId == sighting.animalId && Objects.equals(date, sighting.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, locationId, animalId, date);
    }
}
