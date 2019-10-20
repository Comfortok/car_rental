package com.epam.entity;

public class Transmission extends Entity {
    private String name;

    public Transmission() {
    }

    public Transmission(long id, String name) {
        super(id);
        this.name = name;
    }
}