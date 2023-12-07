import java.sql.SQLException;
import java.util.*;

public class Driver extends Person {

    private int NumberOfTrips;
    private int rating;
    private boolean haveCar;

    public List<Trip> ViewAvailableTrips() {
        List<Trip> trips = new ArrayList<>();

        trips = RetrieveFromDatabase.retrieveAvailableTrips();
        return trips;
    }

    public void AcceptTrip() throws SQLException {
        Map<Integer, Trip> hashMap = new HashMap<>();

        List<Trip> trips = ViewAvailableTrips();
        for (Trip trip : trips) {
            hashMap.put(trip.getTripID(), trip);

            System.out.println("Trip ID: " + trip.getTripID());
            System.out.println("Pickup Point: " + trip.getPickupPoint());
            System.out.println("Destination: " + trip.getDestination());
            System.out.println("Trip Time: " + trip.getTripTime());
            System.out.println("Trip Price: " + trip.getTripPrice());
            System.out.println("Payment Method: " + trip.getPaymentMethod());
            System.out.println("Customer ID: " + trip.getCustomerID());
            System.out.println("------------------------------");
        }

        // Enter trip ID to select it to be assigned to this driver.
        // Can implement a try/catch to prevent the driver from inserting characters
        // other than numbers
        Scanner sc = new Scanner(System.in);
        int tripID = sc.nextInt();

        System.out.println("ASSIGNED TRIP ID = " + tripID);
        System.out.println("DRIVER ID = " + getId());
        Trip assignedTrip = hashMap.get(tripID);
        assignedTrip.setDriverID(getId());
        UpdateDataBase.UpdateTrip(getId(), tripID);
    }

    public List<Trip> ViewTripHistory(int id) {
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isHaveCar() {
        return haveCar;
    }

    public void setHaveCar(boolean haveCar) {
        this.haveCar = haveCar;
    }
}
