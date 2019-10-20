package com.epam.entity;

public class Color extends Entity {
    private String name;

    public Color() {
    }

    public Color(long id, String name) {
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