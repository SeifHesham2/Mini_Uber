import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ID {
    public static int retrieveDriverIdFromDatabase(Driver driver) {
        int driverId = 0;
        DataBaseConnector dataBaseConnector = new DataBaseConnector();
        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "SELECT driverid FROM drivers WHERE email=?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, driver.getEmail());
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        driverId = resultSet.getInt("driverid");
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } finally {
            dataBaseConnector.closeConnection();
        }

        return  driverId;}
    public static int retrieveCarIdFromDatabase(Car car) {
        int carid = 0;
        DataBaseConnector dataBaseConnector = new DataBaseConnector();
        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "SELECT carid FROM cars WHERE plate_number=?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, car.getPlateNumber());
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        carid = resultSet.getInt("carid");
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } finally {
            dataBaseConnector.closeConnection();
        }

        return carid;}
}
