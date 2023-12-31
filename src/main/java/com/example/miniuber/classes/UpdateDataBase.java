package com.example.miniuber.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateDataBase {
    private static final DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();
    public static void UpdateCar(int driverID, int carID) throws SQLException {
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
    public static void UpdateTripStatus(int tripID) throws SQLException {
        Connection connection = dataBaseConnector.connectToDatabase();

        try {
            String sql = "UPDATE trips SET is_finished = true WHERE tripID = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, tripID);
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Trip updated successfully!");
                } else {
                    System.out.println("Failed to update Trip.");
                }
            }
        } finally {
            dataBaseConnector.closeConnection();
        }
    }
    public static void UpdateCarDriverID(int driverID) throws SQLException {
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

    public static Boolean UpdateDriverRating(Driver driver, int driverID, double rating) throws SQLException {
        Connection connection = dataBaseConnector.connectToDatabase();

        try {
            String sql = "UPDATE drivers SET rating = ? WHERE driverID = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setDouble(1, (driver.getRating() + rating) / driver.getNumberOfTrips());
                statement.setInt(2, driverID);
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Driver rating updated successfully!");
                    return true;
                } else {
                    System.out.println("Failed to update driver rating.");
                    return false;
                }
            }
        } finally {
            dataBaseConnector.closeConnection();
        }
    }

    public static void UpdateDriverNumberOfTrips(int driverID, int numberOfTrips) throws SQLException {
        Connection connection = dataBaseConnector.connectToDatabase();

        try {
            String sql = "UPDATE drivers SET number_of_trips = ? WHERE driverID = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, numberOfTrips);
                statement.setInt(2, driverID);
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Driver number of trips updated successfully!");
                } else {
                    System.out.println("Failed to update driver number of trips.");
                }
            }
        } finally {
            dataBaseConnector.closeConnection();
        }
    }

    public static void UpdateTrip(int driverID, int tripID) throws SQLException {
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

    public static Boolean UpdateDriverInfo(Driver driver) throws SQLException {
        Connection connection = dataBaseConnector.connectToDatabase();

        try {
            String sql = "UPDATE drivers SET firstname = ?, lastname = ?, email = ?, phone = ?, driver_password = ? WHERE driverid = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, driver.getFirstName());
                statement.setString(2, driver.getLastName());
                statement.setString(3, driver.getEmail());
                statement.setString(4, driver.getPhone());
                statement.setString(5, driver.getPassword());
                statement.setInt(6, driver.getId());
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Driver updated successfully!");
                    return true;
                } else {
                    System.out.println("Failed to update driver.");
                    return false;
                }
            }
            catch(SQLException e)
            {
                return false;
            }
        } finally {
            dataBaseConnector.closeConnection();
        }
    }

    public static Boolean UpdateCustomer(Customer customer) throws SQLException {
        Connection connection = dataBaseConnector.connectToDatabase();

        try {
            String sql = "UPDATE customers SET firstname = ?, lastname = ?, email = ?, phone = ?, userpassword = ? WHERE customerid = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, customer.getFirstName());
                statement.setString(2, customer.getLastName());
                statement.setString(3, customer.getEmail());
                statement.setString(4, customer.getPhone());
                statement.setString(5, customer.getPassword());
                statement.setInt(6, customer.getId());
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Customer updated successfully!");
                    return true;
                } else {
                    System.out.println("Failed to update customer.");
                    return false;
                }
            }
            catch(SQLException e)
            {
                return false;
            }
        } finally {
            dataBaseConnector.closeConnection();
        }
    }
}
