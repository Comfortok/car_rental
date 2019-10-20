package com.epam.entity;

public class PaymentType extends Entity {
    private String description;

    public PaymentType() {
    }

    public PaymentType(long id, String description) {
        super(id);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}