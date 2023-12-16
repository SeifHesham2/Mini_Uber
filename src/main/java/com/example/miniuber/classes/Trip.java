package com.example.miniuber.classes;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Trip {
    private int tripID;
    private String pickupPoint;
    private String destination;
    private LocalDateTime tripTime;
    private double tripPrice;
    private boolean isFinished;
    private String type;
    private int customerID;
    private int driverID;

    public Trip() throws SQLException {
        UpdateTrip();
    }

    public Trip(int tripID, String pickupPoint, String destination, LocalDateTime tripTime, double tripPrice,
                String type, int customerID) throws SQLException {
        this.tripID = tripID;
        this.pickupPoint = pickupPoint;
        this.destination = destination;
        this.tripTime = tripTime;
        this.tripPrice = tripPrice;
        this.type = type;
        this.customerID = customerID;
        UpdateTrip();
    }

    public Trip(int tripID, int driverID, String pickupPoint, String destination, LocalDateTime tripTime, double tripPrice,
                String type, boolean isFinished) throws SQLException {
        this.tripID = tripID;
        this.pickupPoint = pickupPoint;
        this.destination = destination;
        this.tripTime = tripTime;
        this.tripPrice = tripPrice;
        this.isFinished = isFinished;
        this.type = type;
        this.driverID = driverID;
        UpdateTrip();
    }

    public Trip(int tripID, String pickupPoint, String destination, LocalDateTime tripTime, double tripPrice,
                boolean isFinished, String type, int customerID) throws SQLException {
        this.tripID = tripID;
        this.pickupPoint = pickupPoint;
        this.destination = destination;
        this.tripTime = tripTime;
        this.tripPrice = tripPrice;
        this.isFinished = isFinished;
        this.type = type;
        this.customerID = customerID;
        UpdateTrip();
    }

    public Trip(int tripID, String destination, String pickupPoint, LocalDateTime tripTime, double tripPrice, String type, Boolean isFinished) throws SQLException {
        this.tripID = tripID;
        this.pickupPoint = pickupPoint;
        this.destination = destination;
        this.tripTime = tripTime;
        this.tripPrice = tripPrice;
        this.type = type;
        this.isFinished = isFinished;
        UpdateTrip();
    }

    public Trip(int tripID, String destination, String pickupPoint, LocalDateTime tripTime, double tripPrice, String type) throws SQLException {
        this.tripID = tripID;
        this.pickupPoint = pickupPoint;
        this.destination = destination;
        this.tripTime = tripTime;
        this.tripPrice = tripPrice;
        this.type = type;
        UpdateTrip();
    }

    private void UpdateTrip() throws SQLException {
        LocalDateTime currentDateTime = LocalDateTime.now();
        if (this.tripTime != null && currentDateTime.isAfter(this.tripTime)) {
            this.isFinished = true;
            UpdateDataBase.UpdateTripStatus(this.tripID);
        }
    }

    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    public String getPickupPoint() {
        return pickupPoint;
    }

    public void setPickupPoint(String pickupPoint) {
        this.pickupPoint = pickupPoint;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getTripTime() {
        return tripTime;
    }

    public void setTripTime(String tripTime) {
        this.tripTime = LocalDateTime.parse(tripTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public double getTripPrice() {
        return tripPrice;
    }

    public void setTripPrice(double tripPrice) {
        this.tripPrice = tripPrice;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public String getType() {
        return type;
    }

    public void setPaymentMethod(String type) {
        this.type = type;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }
}
