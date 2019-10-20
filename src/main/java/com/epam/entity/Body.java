package com.epam.entity;

public class Body extends Entity {
    private String name;

    public Body() {
    }

    public Body(long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}