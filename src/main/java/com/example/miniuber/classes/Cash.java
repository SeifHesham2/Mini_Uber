package com.example.miniuber.classes;

import java.time.YearMonth;

public class Cash extends PaymentStrategy {
    public Cash() {
    }

    public Cash(String cardNumber, String cvv, YearMonth expirationDate, int customerID) {
        super(cardNumber, cvv, expirationDate, customerID);
    }

    @Override
    public double totalPayment(double price) {
        return  price;
    }

    @Override
    public String getType() {
        return "Cash";
    }
}
