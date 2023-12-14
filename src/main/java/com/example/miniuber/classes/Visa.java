package com.example.miniuber.classes;

public class Visa implements PaymentMethod {
    @Override
    public String getType() {
        return "Visa";
    }
}
