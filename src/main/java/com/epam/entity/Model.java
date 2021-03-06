package com.epam.entity;

public class Model extends Entity {
    private Brand brand;
    private String name;

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return brand.getName() + " " + getName();
    }
}