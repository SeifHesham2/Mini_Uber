package com.example.miniuber.classes;

import java.sql.SQLException;

public class Register {
    public static Boolean register(String firstName, String lastName, String email, String phone, String password) {
        Customer customer = new Customer(firstName, lastName, email, phone, password);
        Boolean done = false;
        try {
            done = InsertToDatabase.InsertCustomer(customer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return done;
    }
}
