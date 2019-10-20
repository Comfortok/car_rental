package com.epam.entity;

public class Location extends Entity {
    private String name;
    private String city;
    private String address;

    public Location() {
    }

    public Location(long id, String name, String city, String address) {
        super(id);
        this.name = name;
        this.city = city;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}