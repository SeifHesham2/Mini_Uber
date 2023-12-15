package com.example.miniuber.classes;

import java.time.YearMonth;

public class Visa extends PaymentStrategy {
    public Visa() {
    }

    @Override
    public double totalPayment(double price) {
         return price*1.03;
    }

    public Visa(String cardNumber, String cvv, YearMonth expirationDate, int customerID) {
        super(cardNumber, cvv, expirationDate, customerID);
    }

    @Override
    public String getType() {
        return "Visa";
    }
}
