package com.sg.superhero.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class Location {


    private int id;
    @NotBlank(message = "Name must not be blank")
    @Size(max = 30, message="Name must be less than 50 characters.")
    private String name;
    @NotBlank(message = "Description must not be blank")
    @Size(max = 50, message="Description must be less than 50 characters.")
    private String description;
    @NotBlank(message = "Address must not be blank")
    @Size(max = 50, message="Address must be less than 50 characters.")
    private String address;
    @Digits(integer=2, fraction=6, message = "Can only have up to 2 integer digits and 6 fractional digits in latitude.")
    private float latitude;
    @Digits(integer=2, fraction=6, message = "Can only have up to 2 integer digits and 6 fractional digits in longitude.")
    private float longitude;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return id == location.id && Float.compare(location.latitude, latitude) == 0 && Float.compare(location.longitude, longitude) == 0 && name.equals(location.name) && Objects.equals(description, location.description) && address.equals(location.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, address, latitude, longitude);
    }
}
