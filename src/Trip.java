import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Trip {
    private int tripID;
    private String pickupPoint;
    private String destination;
    private LocalDateTime tripTime;
    private double tripPrice;
    private boolean isFinished;
    private PaymentMethod paymentMethod;
    private int customerID;
    private int driverID;

    public Trip() {

    }

    public Trip(int tripID, String pickupPoint, String destination, LocalDateTime tripTime, double tripPrice,
            PaymentMethod paymentMethod, int customerID) {
        this.tripID = tripID;
        this.pickupPoint = pickupPoint;
        this.destination = destination;
        this.tripTime = tripTime;
        this.tripPrice = tripPrice;
        this.paymentMethod = paymentMethod;
        this.customerID = customerID;
    }

    public Trip(int tripID, String pickupPoint, String destination, LocalDateTime tripTime, double tripPrice,
            boolean isFinished, PaymentMethod paymentMethod, int customerID) {
        this.tripID = tripID;
        this.pickupPoint = pickupPoint;
        this.destination = destination;
        this.tripTime = tripTime;
        this.tripPrice = tripPrice;
        this.isFinished = isFinished;
        this.paymentMethod = paymentMethod;
        this.customerID = customerID;
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

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
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
