package com.example.miniuber.classes;

public class PaymentFactory
{
    public static PaymentMethod getPaymentMethod(String PaymentType) {
        if(PaymentType.equalsIgnoreCase("visa")){
            return  new Visa();
        }
        else if (PaymentType.equalsIgnoreCase("PayPal")){
            return  new Paypal();
        }
        else if (PaymentType.equalsIgnoreCase("Cash")){
            return  new Cash();
        }
        else {
            //throw new IllegalArgumentException("this PaymentMethod is not supported yet !");
            return null;
        }
    }
}
