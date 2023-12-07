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
                        Trip trip = new Trip(rS.getInt("tripID"), rS.getString("pickup_point"), rS.getString("destination"), rS.getTimestamp("trip_time").toLocalDateTime(), rS.getDouble("trip_price"), PaymentFactory.getPaymentMethod("payment_method"), rS.getInt("customerID"));
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

    public static List<Trip> retrieveTripsHistory(int id) {
        List<Trip> trips = new ArrayList<>();

        DataBaseConnector dataBaseConnector = new DataBaseConnector();
        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "SELECT * FROM trips WHERE driverID = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet rS = statement.executeQuery()) {
                    while (rS.next()) {
                        Trip trip = new Trip(rS.getInt("tripID"), rS.getString("pickup_point"), rS.getString("destination"), rS.getTimestamp("trip_time").toLocalDateTime(), rS.getDouble("trip_price"), rS.getBoolean("is_finished"), PaymentFactory.getPaymentMethod("payment_method"), rS.getInt("customerID"));
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
