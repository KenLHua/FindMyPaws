package com.sg.superhero.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class Organization {

    private int id;

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", heroes=" + heroes +
                '}';
    }

    @NotBlank(message = "Name must not be blank.")
    @Size(max = 30, message="Location name must be less than 50 characters.")
    private String name;
    @NotBlank(message = "Description must not be blank.")
    @Size(max = 50, message="Description must be less than 50 characters.")
    private String description;
    @NotBlank(message = "Contact Info must not be blank.")
    @Size(max = 50, message="Contact Info must be less than 50 characters.")
    private String contactInfo;
    private List<Hero> heroes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return id == that.id && name.equals(that.name) && Objects.equals(description, that.description) && Objects.equals(contactInfo, that.contactInfo) && Objects.equals(heroes, that.heroes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, contactInfo, heroes);
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

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }
}
