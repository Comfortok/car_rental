package com.epam.entity;

public class Invoice extends Entity {
    private String date;
    private Order order;
    private PaymentType paymentType;
    private double totalAmount;
    private boolean isPaid;

    public Invoice() {
    }

    public Invoice(long id, String date, Order order, PaymentType paymentType,
                   double totalAmount, boolean isPaid) {
        super(id);
        this.date = date;
        this.order = order;
        this.paymentType = paymentType;
        this.totalAmount = totalAmount;
        this.isPaid = isPaid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}