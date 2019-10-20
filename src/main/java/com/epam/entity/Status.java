package com.epam.entity;

public class Status extends Entity {
    private String name;

    public Status() {
    }

    public Status(long id, String name) {
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