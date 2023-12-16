package com.example.miniuber.classes;

public class PaymentProcessor {
    private PaymentStrategy paymentStrategy;

    public PaymentProcessor(PaymentStrategy paymentStrategy)
    {
        this.paymentStrategy = paymentStrategy;
    }
    public double processPayment(double price)
    {
        return paymentStrategy.totalPayment(price);
    }
    public PaymentStrategy getPaymentStrategy()
    {
        return paymentStrategy;
    }
    public void setPaymentStrategy(PaymentStrategy paymentStrategy)
    {
        this.paymentStrategy = paymentStrategy;
    }
}