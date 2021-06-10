package com.sg.findmypaws.model;

import java.util.Objects;

public class Filter {
    public Location loc;
    public int radius;
    public int daysAgo;
    public Animal mockAnimal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filter filter = (Filter) o;
        return radius == filter.radius && daysAgo == filter.daysAgo && Objects.equals(loc, filter.loc) && Objects.equals(mockAnimal, filter.mockAnimal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loc, radius, daysAgo, mockAnimal);
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getDaysAgo() {
        return daysAgo;
    }

    public void setDaysAgo(int daysAgo) {
        this.daysAgo = daysAgo;
    }

    public Animal getMockAnimal() {
        return mockAnimal;
    }

    public void setMockAnimal(Animal mockAnimal) {
        this.mockAnimal = mockAnimal;
    }
}
