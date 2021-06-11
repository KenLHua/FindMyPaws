package com.sg.findmypaws.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Animal {
    private int id;
    private Owner owner;
    private LocalDate date;
    private Integer status;

    private String name;
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




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Boolean hasNameTag() {
        return nameTag;
    }

    public void setNameTag(boolean nametag) {
        this.nameTag = nameTag;
    }


    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public Boolean isFemale() {
        return female;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setFemale(boolean female) {
        this.female = female;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id && status == animal.status && height == animal.height && weight == animal.weight && age == animal.age && Objects.equals(owner, animal.owner) && Objects.equals(date, animal.date) && Objects.equals(name, animal.name) && Objects.equals(breed, animal.breed) && Objects.equals(species, animal.species) && Objects.equals(color, animal.color) && Objects.equals(description, animal.description) && Objects.equals(hash, animal.hash) && Objects.equals(image, animal.image) && Objects.equals(female, animal.female) && Objects.equals(nameTag, animal.nameTag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owner, date, status, name, breed, species, color, description, hash, image, height, weight, age, female, nameTag);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", owner=" + owner +
                ", date=" + date +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", species='" + species + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", hash='" + hash + '\'' +
                ", image='" + image + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", age=" + age +
                ", female=" + female +
                ", nameTag=" + nameTag +
                '}';
    }
}
