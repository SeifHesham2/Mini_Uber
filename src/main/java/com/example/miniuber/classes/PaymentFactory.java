package com.example.miniuber.classes;

public class PaymentFactory
{
    public static PaymentStrategy getPaymentMethod(String PaymentType) {
        if(PaymentType.equalsIgnoreCase("visa")){
            return  new Visa();
        }
        else if (PaymentType.equalsIgnoreCase("PayPal")){
            return  new Paypal();
        }
        else if (PaymentType.equalsIgnoreCase("Cash")){
            return  new Cash();
        }
        else return null;
    }
}
