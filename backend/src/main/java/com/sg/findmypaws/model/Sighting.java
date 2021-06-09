package com.sg.findmypaws.model;

import java.sql.Date;
import java.time.LocalDate;

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
}
