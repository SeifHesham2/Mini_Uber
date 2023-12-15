package com.example.miniuber.classes;

import java.time.YearMonth;

public class Paypal extends PaymentMethod {
    public Paypal() {
    }

    public Paypal(String cardNumber, String cvv, YearMonth expirationDate, int customerID) {
        super(cardNumber, cvv, expirationDate, customerID);
    }

    @Override
    public String getType() {
        return "Paypal";
    }
}
