package com.epam.entity;

import java.sql.Date;

public class Driver extends Entity {
    private String name;
    private String surname;
    private Date dateOfBirth;
    private String phoneNumber;
    private Passport passport;
    private DrivingLicence drivingLicence;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public DrivingLicence getDrivingLicence() {
        return drivingLicence;
    }

    public void setDrivingLicence(DrivingLicence drivingLicence) {
        this.drivingLicence = drivingLicence;
    }
}