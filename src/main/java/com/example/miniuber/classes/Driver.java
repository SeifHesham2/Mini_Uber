package com.example.miniuber.classes;

import java.sql.SQLException;
import java.util.*;

public class Driver extends Person {

    private int NumberOfTrips;
    private boolean haveCar;
    private double rating;

    public Driver() {
    }

    public Driver(String firstName, String lastName, String email, String password, String phone) {
        super(firstName, lastName, email, password, phone);
    }

    public Driver(String firstName, String lastName, String email, String password, String phone, int id, int numberOfTrips, boolean haveCar, double rating) {
        super(firstName, lastName, email, password, phone, id);
        NumberOfTrips = numberOfTrips;
        this.haveCar = haveCar;
        this.rating = rating;
    }

    public static List<Trip> ViewAvailableTrips() {
        List<Trip> trips = new ArrayList<>();

        trips = RetrieveFromDatabase.retrieveAvailableTrips();
        return trips;
    }

    public static Boolean updateInfo(String firstName, String lastName, String email, String phone, String password, Driver driver) throws SQLException {
        driver.setFirstName(firstName);
        driver.setLastName(lastName);
        driver.setEmail(email);
        driver.setPhone(phone);
        driver.setPassword(password);

        Boolean valid = UpdateDataBase.UpdateDriverInfo(driver);
        return valid;
    }

    public static void AcceptTrip(Driver driver, int tripID) throws SQLException {
        Map<Integer, Trip> hashMap = new HashMap<>();

        List<Trip> trips = ViewAvailableTrips();
        for (Trip trip : trips) {
            hashMap.put(trip.getTripID(), trip);

            System.out.println("Trip ID: " + trip.getTripID());
            System.out.println("Pickup Point: " + trip.getPickupPoint());
            System.out.println("Destination: " + trip.getDestination());
            System.out.println("Trip Time: " + trip.getTripTime());
            System.out.println("Trip Price: " + trip.getTripPrice());
            System.out.println("Payment Method: " + trip.getType());
            System.out.println("Customer ID: " + trip.getCustomerID());
            System.out.println("------------------------------");
        }

        // Enter trip ID to select it to be assigned to this driver.
        // Can implement a try/catch to prevent the driver from inserting characters
        // other than numbers

        System.out.println("ASSIGNED TRIP ID = " + tripID);
        System.out.println("DRIVER ID = " + driver.getId());
        Trip assignedTrip = hashMap.get(tripID);
        assignedTrip.setDriverID(driver.getId());
        UpdateDataBase.UpdateTrip(driver.getId(), tripID);
        driver.setNumberOfTrips(driver.getNumberOfTrips() + 1);
    }

    public static List<Trip> ViewTripHistory(int id) {
        List<Trip> trips = new ArrayList<>();
        trips = RetrieveFromDatabase.retrieveDriverTripsHistory(id);
        return trips;
    }

    public int getNumberOfTrips() {
        return NumberOfTrips;
    }

    public void setNumberOfTrips(int numberOfTrips) {
        NumberOfTrips = numberOfTrips;
    }

    public boolean isHaveCar() {
        return haveCar;
    }

    public void setHaveCar(boolean haveCar) {
        this.haveCar = haveCar;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
