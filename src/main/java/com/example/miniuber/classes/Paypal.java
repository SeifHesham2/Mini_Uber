package com.example.miniuber.classes;

public class Paypal implements PaymentMethod {
    @Override
    public String getType() {
        return "Paypal";
    }
}
