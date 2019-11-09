package com.epam.entity;

import java.sql.Date;

public class Passport {
    private String number;
    private Date dateOfIssue;
    private Date dateOfExpiry;
    private String authority;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Date getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(Date dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Passport number: " + getNumber() + "\n"
                + "Issue: " + getDateOfIssue() + "\n"
                + "Expiry: " + getDateOfExpiry() + "\n"
                + "Authority: " + getAuthority() + "\n";
    }
}