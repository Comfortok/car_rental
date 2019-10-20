package com.epam.entity;

public class CarCategory extends Entity {
    private String name;
    private double pricePerDay;

    public CarCategory() {
    }

    public CarCategory(long id, String name, double pricePerDay) {
        super(id);
        this.name = name;
        this.pricePerDay = pricePerDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @Override
    public String toString() {
        return getName();
    }
}