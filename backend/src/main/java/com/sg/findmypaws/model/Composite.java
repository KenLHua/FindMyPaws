package com.sg.findmypaws.model;

import java.time.LocalDate;

public class Composite {

    private String name;
    private LocalDate lastSeen;
    private float lat;
    private float lon;
    private int animalId;

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(LocalDate lastSeen) {
        this.lastSeen = lastSeen;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public Composite(String name, LocalDate lastSeen, float lat, float lon, int animalId) {
        this.name = name;
        this.lastSeen = lastSeen;
        this.lat = lat;
        this.lon = lon;
        this.animalId = animalId;
    }
}