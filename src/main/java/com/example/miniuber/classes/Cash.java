package com.example.miniuber.classes;

public class Cash implements PaymentMethod {
    @Override
    public String getType() {
        return "Cash";
    }
}
