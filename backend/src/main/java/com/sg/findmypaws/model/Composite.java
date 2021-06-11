package com.sg.findmypaws.model;

import java.time.LocalDate;

public class Composite {

    private String name;
    private LocalDate lastSeen;
    private float lat;
    private float lon;
    private int animalId;

    private LocalDate date;
    private Integer status;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean getFemale() {
        return female;
    }

    public void setFemale(Boolean female) {
        this.female = female;
    }

    public Boolean getNameTag() {
        return nameTag;
    }

    public void setNameTag(Boolean nameTag) {
        this.nameTag = nameTag;
    }

    private String breed;
    private String species;
    private String color;
    private String description;

    private String hash;

    private String image;
    private int height;
    private int weight;


    private int age;
    private Boolean female;
    private Boolean nameTag;

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

    public Composite(String name, LocalDate lastSeen, float lat, float lon, int animalId, LocalDate date, Integer status, String breed, String species, String color, String description, String hash, String image, int height, int weight, int age, Boolean female, Boolean nameTag) {
        this.name = name;
        this.lastSeen = lastSeen;
        this.lat = lat;
        this.lon = lon;
        this.animalId = animalId;
        this.date = date;
        this.status = status;
        this.breed = breed;
        this.species = species;
        this.color = color;
        this.description = description;
        this.hash = hash;
        this.image = image;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.female = female;
        this.nameTag = nameTag;
    }
}