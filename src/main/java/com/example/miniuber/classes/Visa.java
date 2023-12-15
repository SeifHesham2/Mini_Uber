package com.example.miniuber.classes;

import java.time.YearMonth;

public class Visa extends PaymentMethod {
    public Visa() {
    }

    public Visa(String cardNumber, String cvv, YearMonth expirationDate, int customerID) {
        super(cardNumber, cvv, expirationDate, customerID);
    }

    @Override
    public String getType() {
        return "Visa";
    }
}
