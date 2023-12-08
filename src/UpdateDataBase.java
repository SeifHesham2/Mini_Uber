import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateDataBase {
    public static void UpdateCar(int driverID, int carID) throws SQLException {
        DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();
        Connection connection = dataBaseConnector.connectToDatabase();

        try {
            String sql = "UPDATE cars SET DriverId = ? WHERE carId = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, driverID);
                statement.setInt(2, carID);
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Car updated successfully!");
                } else {
                    System.out.println("Failed to update Car.");
                }
            }
        } finally {
            dataBaseConnector.closeConnection();
        }
    }
    public static void UpdateCarDriverID(int driverID) throws SQLException {
        DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();
        Connection connection = dataBaseConnector.connectToDatabase();

        try {
            String sql = "UPDATE cars SET DriverId = NULL WHERE driverID = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, driverID);
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("CarDriverID updated successfully!");
                } else {
                    System.out.println("Failed to update CarDriverID.");
                }
            }
        } finally {
            dataBaseConnector.closeConnection();
        }
    }
    public static void UpdateComplaintsStatus(int complaintsID) throws SQLException {
        DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();
        Connection connection = dataBaseConnector.connectToDatabase();

        try {
            String sql = "UPDATE complaints SET opened = 1 WHERE complaintId = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, complaintsID);
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Complaints status updated successfully!");
                } else {
                    System.out.println("Failed to update Complaints status.");
                }
            }
        } finally {
            dataBaseConnector.closeConnection();
        }
    }
    public static void UpdateDriver(Boolean haveCar, int driverID) throws SQLException {
        DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();
        Connection connection = dataBaseConnector.connectToDatabase();

        try {
            String sql = "UPDATE drivers SET have_car = ? WHERE driverID = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setBoolean(1, haveCar);
                statement.setInt(2, driverID);
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("driver updated successfully!");
                } else {
                    System.out.println("Failed to update driver.");
                }
            }
        } finally {
            dataBaseConnector.closeConnection();
        }
    }

    public static void UpdateTrip(int driverID, int tripID) throws SQLException {
        DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();
        Connection connection = dataBaseConnector.connectToDatabase();

        try {
            String sql = "UPDATE trips SET driverID = ? WHERE tripID = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, driverID);
                statement.setInt(2, tripID);
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Trip updated successfully!");
                } else {
                    System.out.println("Failed to update trip.");
                }
            }
        } finally {
            dataBaseConnector.closeConnection();
        }
    }
}
