import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateDataBase {
    public static void UpdateCar(Car car , Driver driver) throws SQLException {
        DataBaseConnector dataBaseConnector = new DataBaseConnector();
        Connection connection = dataBaseConnector.connectToDatabase();

        try {
            String sql = "UPDATE cars SET DriverId = ? WHERE carId = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                System.out.println(driver.getId());
                System.out.println(car.getCarID());
                statement.setInt(1, driver.getId());
                statement.setInt(2, car.getCarID());
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
    public static void UpdateDriver( Driver driver) throws SQLException {
        DataBaseConnector dataBaseConnector = new DataBaseConnector();
        Connection connection = dataBaseConnector.connectToDatabase();

        try {
            String sql = "UPDATE drivers SET have_car = ? WHERE driverID = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setBoolean(1,driver.isHaveCar());
                statement.setInt(2, driver.getId());
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
}
