package com.example.miniuber.classes;

import java.sql.*;

public class DataBaseAuthentication {
    private static final DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();

    public static int DriverAuthentication(String email, String password) {
        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "SELECT driverID FROM drivers WHERE driver_password = ? and email = ?;";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, password);
                statement.setString(2, email);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("driverID");
                    } else {
                        return -1;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } finally {
            dataBaseConnector.closeConnection();
        }
    }

    public static int CustomerAuthentication(String email, String password) {
        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "SELECT CustomerID FROM customers WHERE UserPassword = ? and email = ?;";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, password);
                statement.setString(2, email);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("CustomerID");
                    } else {
                        return -1;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } finally {
            dataBaseConnector.closeConnection();
        }
    }

    public static int EmployeeAuthentication(String email, String password) {
        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "SELECT EmployeeID FROM employees WHERE EmployeePassword = ? and email = ?;";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, password);
                statement.setString(2, email);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("EmployeeID");
                    } else {
                        return -1;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } finally {
            dataBaseConnector.closeConnection();
        }
    }
}