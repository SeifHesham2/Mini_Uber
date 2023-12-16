package com.example.miniuber.classes;

import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Customer extends Person {
    public Customer() {
    }

    public Customer(String firstName, String lastName, String email, String password, String phone, int id) {
        super(firstName, lastName, email, password, phone, id);
    }

    public Customer(String firstName, String lastName, String email, String phone, String password) {
        super(firstName, lastName, email, password, phone);
    }

    public static List<Trip> PreviousTripsDetails(int id) {
        List<Trip> trips = new ArrayList<>();
        trips = RetrieveFromDatabase.retrieveCustomerTripsHistory(id);
        return trips;
    }

    public static Boolean RequestTrip(String pickupPoint, String destination, String tripTime, double tripPrice,
                                      PaymentStrategy paymentStrategy, int customerID) throws SQLException {
        Trip trip = new Trip();
        trip.setPickupPoint(pickupPoint);
        trip.setDestination(destination);
        trip.setTripPrice(tripPrice);
        trip.setPaymentMethod(paymentStrategy);
        trip.setCustomerID(customerID);
        trip.setTripTime(tripTime);

        Boolean valid = InsertToDatabase.InsertTrip(trip);
        if(valid)
        {
            System.out.println("Waiting for Driver to accept the trip");
            return true;
        }
        else
            return false;
    }

    public static Boolean SendComplaint(String description, int tripId, int customerID, Label errorLabel) throws SQLException {
        int customerid = RetrieveFromDatabase.retrieveTrip(tripId);
        if(customerid != customerID)
        {
            System.out.println("You don't have this trip in your account.");
            errorLabel.setText("You don't have this trip in your account.");
            return false;
        }
        Complaints complaint = new Complaints();
        complaint.setDescription(description);
        complaint.setTripID(tripId);
        InsertToDatabase.InsertComplaint(complaint);
        System.out.println("Complaint sent successfully waiting for admin to respond!");
        return true;
    }

    public static Boolean updateInfo(String firstName, String lastName, String email, String phone, String password, Customer customer) throws SQLException {
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setPassword(password);

        Boolean valid = UpdateDataBase.UpdateCustomer(customer);
        return valid;
    }

}
