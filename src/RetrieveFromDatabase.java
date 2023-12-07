import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class RetrieveFromDatabase {
    public static List<Trip> retrieveAvailableTrips() {
        List<Trip> trips = new ArrayList<>();

        DataBaseConnector dataBaseConnector = new DataBaseConnector();
        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "SELECT * FROM trips WHERE driverID IS NULL;";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet rS = statement.executeQuery()) {
                    while (rS.next()) {
                        Trip trip = new Trip(rS.getInt("tripID"), rS.getString("pickup_point"),
                                rS.getString("destination"), rS.getTimestamp("trip_time").toLocalDateTime(),
                                rS.getDouble("trip_price"), PaymentFactory.getPaymentMethod("payment_method"),
                                rS.getInt("customerID"));
                        trips.add(trip);
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } finally {
            dataBaseConnector.closeConnection();
        }

        return trips;
    }

    public static List<Car> retrieveAvailableCars() {
        List<Car> carList = new ArrayList<>();

        DataBaseConnector dataBaseConnector = new DataBaseConnector();
        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "SELECT * FROM cars WHERE driverID IS NULL;";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet rS = statement.executeQuery()) {
                    while (rS.next()) {
                        Car car = new Car(rS.getInt("carID"), rS.getInt("number_of_seats"),
                                rS.getString("plate_number"), rS.getString("car_type"), rS.getString("car_color"),
                                rS.getString("car_model"), rS.getInt("driverID"));
                        carList.add(car);
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } finally {
            dataBaseConnector.closeConnection();
        }

        return carList;
    }

    public static List<Complaints> retrieveComplaints() {
        List<Complaints> complaintsList = new ArrayList<>();

        DataBaseConnector dataBaseConnector = new DataBaseConnector();
        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "SELECT * FROM complaints;";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet rS = statement.executeQuery()) {
                    while (rS.next()) {
                        Complaints complaints = new Complaints(rS.getInt("complaintid"), rS.getInt("tripid"),
                                rS.getString("Description"), rS.getBoolean("opened"));
                        complaintsList.add(complaints);
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } finally {
            dataBaseConnector.closeConnection();
        }

        return complaintsList;
    }

    public static List<Trip> retrieveDriverTripsHistory(int id) {
        List<Trip> trips = new ArrayList<>();

        DataBaseConnector dataBaseConnector = new DataBaseConnector();
        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "SELECT * FROM trips WHERE driverID = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet rS = statement.executeQuery()) {
                    while (rS.next()) {
                        Trip trip = new Trip(rS.getInt("tripID"), rS.getString("pickup_point"),
                                rS.getString("destination"), rS.getTimestamp("trip_time").toLocalDateTime(),
                                rS.getDouble("trip_price"), rS.getBoolean("is_finished"),
                                PaymentFactory.getPaymentMethod("payment_method"), rS.getInt("customerID"));
                        trips.add(trip);
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } finally {
            dataBaseConnector.closeConnection();
        }

        return trips;
    }

    public static List<Trip> retrieveCustomerTripsHistory(int id) {
        List<Trip> trips = new ArrayList<>();

        DataBaseConnector dataBaseConnector = new DataBaseConnector();
        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "SELECT * FROM trips WHERE customerID = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet rS = statement.executeQuery()) {
                    while (rS.next()) {
                        Trip trip = new Trip(rS.getInt("tripID"), rS.getString("pickup_point"),
                                rS.getString("destination"), rS.getTimestamp("trip_time").toLocalDateTime(),
                                rS.getDouble("trip_price"), rS.getBoolean("is_finished"),
                                PaymentFactory.getPaymentMethod("payment_method"), rS.getInt("customerID"));
                        trips.add(trip);
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } finally {
            dataBaseConnector.closeConnection();
        }

        return trips;
    }
}
