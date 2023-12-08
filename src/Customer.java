import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Customer extends Person {
    private int rating;
    private double balance;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String email, String password, String phone, int id, int rating, double balance) {
        super(firstName, lastName, email, password, phone, id);
        this.rating = rating;
        this.balance = balance;
    }

    public List<Trip> PreviousTripsDetails(int id) {
        List<Trip> trips = new ArrayList<>();
        trips = RetrieveFromDatabase.retrieveCustomerTripsHistory(id);
        return trips;
    }

    public void AddBalance(double amount) {
        this.balance += amount;
    }

    public Trip RequestTrip(String pickupPoint, String destination, String tripTime, double tripPrice,
            PaymentMethod paymentMethod, int customerID) throws SQLException {
        Trip trip = new Trip();
        trip.setPickupPoint(pickupPoint);
        trip.setDestination(destination);
        trip.setTripPrice(tripPrice);
        trip.setPaymentMethod(paymentMethod);
        trip.setCustomerID(customerID);
        trip.setTripTime(tripTime);

        InsertToDatabase.InsertTrip(trip);
        System.out.println("Waiting for Driver to accept the trip");
        return trip;
    }

    public void RateDriver(int id, int rating) {
        // TODO
    }

    public void SendComplaint(String description, int tripId) throws SQLException {
        Complaints complaint = new Complaints();
        complaint.setDescription(description);
        complaint.setTripID(tripId);
        InsertToDatabase.InsertComplaint(complaint);
        System.out.println("Complaint sent successfully waiting for admin to respond!");
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
