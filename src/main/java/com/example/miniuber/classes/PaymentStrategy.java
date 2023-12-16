package com.example.miniuber.classes;

import java.time.YearMonth;

public abstract class PaymentStrategy {
    private int cardID;
    private String cardNumber;
    private String cvv;
    private YearMonth expirationDate;
    private int customerID;

    public abstract String getType();

    public PaymentStrategy() {
    }

    public PaymentStrategy(String cardNumber, String cvv, YearMonth expirationDate, int customerID) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.customerID = customerID;
    }
    public  abstract double totalPayment(double price);

    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public YearMonth getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(YearMonth expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCustomerID() {
        return customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getCardID() {
        return cardID;
    }
    public void setCardID(int cardID) {
        this.cardID = cardID;
    }
}
