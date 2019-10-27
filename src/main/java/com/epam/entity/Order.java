package com.epam.entity;

import java.sql.Date;
import java.util.List;

public class Order extends Entity {
    private User user;
    private Car car;
    private Driver driver;
    private Status status;
    private Date startDate;
    private Date endDate;
    private Location pickUp;
    private Location dropOff;
    private List<Driver> driverList;
    private long userID;
    private long carID;
    private double price;
    private double paymentSum;

    public Order() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User client) {
        this.user = client;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Location getPickUp() {
        return pickUp;
    }

    public void setPickUp(Location pickUp) {
        this.pickUp = pickUp;
    }

    public Location getDropOff() {
        return dropOff;
    }

    public void setDropOff(Location dropOff) {
        this.dropOff = dropOff;
    }

    public List<Driver> getDriverList() {
        return driverList;
    }

    public void setDriverList(List<Driver> driverList) {
        this.driverList = driverList;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getCarID() {
        return carID;
    }

    public void setCarID(long carID) {
        this.carID = carID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(double paymentSum) {
        this.paymentSum = paymentSum;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "Order id: " + getId() + " user: " + user.getId() + " car: " + car.getId() +
                " status id: " + status.getId() + " sum: " + paymentSum;
    }
}