package com.epam.entity;

public class Brand extends Entity {
    private String name;

    public Brand() {
    }

    public Brand(long id, String name) {
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