package com.example.miniuber.classes;

import java.time.YearMonth;

public class Paypal extends PaymentStrategy {
    public Paypal() {
    }
    @Override
    public double totalPayment(double price) {
        return price*1.05;
    }

    public Paypal(String cardNumber, String cvv, YearMonth expirationDate, int customerID) {
        super(cardNumber, cvv, expirationDate, customerID);
    }

    @Override
    public String getType() {
        return "Paypal";
    }
}
