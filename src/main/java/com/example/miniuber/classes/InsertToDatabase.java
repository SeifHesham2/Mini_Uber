package com.example.miniuber.classes;

import java.sql.*;
import java.time.LocalDate;
import java.time.YearMonth;

public class InsertToDatabase {
    private static final DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();

    public static Boolean InsertDriver(Driver driver) throws SQLException {
        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "INSERT INTO drivers (firstname , lastname , email , driver_password , phone) VALUES ( ? , ? , ? , ? , ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, driver.getFirstName());
                statement.setString(2, driver.getLastName());
                statement.setString(3, driver.getEmail());
                statement.setString(4, driver.getPassword());
                statement.setString(5, driver.getPhone());
                try{
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                int autoIncrementedID = generatedKeys.getInt(1);
                                System.out.println("Auto-incremented ID: " + autoIncrementedID);
                                driver.setId(autoIncrementedID);
                            } else {
                                System.out.println("Failed to retrieve auto-incremented ID.");
                            }
                        }
                        System.out.println("Driver inserted successfully!");
                        return true;
                    }
                } catch(SQLException e){
                    System.out.println("Failed to insert Driver.");
                    return false;
                }
            }
        } finally {
            dataBaseConnector.closeConnection();
        }
        return false;
    }

    public static Boolean InsertCustomer(Customer customer) throws SQLException {
        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "INSERT INTO customers (firstname , lastname , email , phone , UserPassword) VALUES ( ? , ? , ? , ? , ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, customer.getFirstName());
                statement.setString(2, customer.getLastName());
                statement.setString(3, customer.getEmail());
                statement.setString(4, customer.getPhone());
                statement.setString(5, customer.getPassword());
                try{
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                int autoIncrementedID = generatedKeys.getInt(1);
                                System.out.println("Auto-incremented ID: " + autoIncrementedID);
                                customer.setId(autoIncrementedID);
                            } else {
                                System.out.println("Failed to retrieve auto-incremented ID.");
                            }
                        }
                        System.out.println("Customer inserted successfully!");
                        return true;
                    }
                } catch(SQLException e){
                    System.out.println("Failed to insert Customer.");
                    return false;
                }
            }
        } finally {
            dataBaseConnector.closeConnection();
        }
        return false;
    }

    public static Boolean InsertTrip(Trip trip) throws SQLException {
        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "INSERT INTO trips (pickup_point , destination , trip_time , trip_price , payment_method , customerID ) VALUES ( ? , ? , ? , ? , ? , ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                System.out.println("PASSED");
                statement.setString(1, trip.getPickupPoint());
                statement.setString(2, trip.getDestination());
                Timestamp timestamp = Timestamp.valueOf(trip.getTripTime());
                statement.setTimestamp(3, timestamp);
                statement.setDouble(4, trip.getTripPrice());
                statement.setString(5, trip.getPaymentMethod().getType());
                statement.setString(6, Integer.toString(trip.getCustomerID()));
                try{
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                int autoIncrementedID = generatedKeys.getInt(1);
                                System.out.println("Auto-incremented ID: " + autoIncrementedID);
                                trip.setTripID(autoIncrementedID);
                            } else {
                                System.out.println("Failed to retrieve auto-incremented ID.");
                            }
                        }
                        System.out.println("Trip inserted successfully!");
                        return true;
                    }
                } catch(SQLException e){
                    System.out.println("Failed to insert Trip.");
                    return false;
                }
            }
        } finally {
            dataBaseConnector.closeConnection();
        }
        return false;
    }

    public static Boolean InsertCar(Car car) throws SQLException {
        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "INSERT INTO cars (plate_number , car_type , car_color , car_model , number_of_seats  ) VALUES ( ? , ? , ? , ? , ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, car.getPlateNumber());
                statement.setString(2, car.getCarType());
                statement.setString(3, car.getCarColor());
                statement.setString(4, car.getCarModel());
                statement.setInt(5, car.getNumberOfSeats());
                try{
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                int autoIncrementedID = generatedKeys.getInt(1);
                                System.out.println("Auto-incremented ID: " + autoIncrementedID);
                                car.setCarID(autoIncrementedID);
                            } else {
                                System.out.println("Failed to retrieve auto-incremented ID.");
                            }
                        }
                        System.out.println("Car inserted successfully!");
                        return true;
                    }
                } catch(SQLException e){
                    System.out.println("Failed to insert Car.");
                    return false;
                }
            }
        } finally {
            dataBaseConnector.closeConnection();
        }
        return false;
    }

    public static Boolean InsertComplaint(Complaints complaints) throws SQLException {
        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "INSERT INTO complaints (tripID, description) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, Integer.toString(complaints.getTripID()));
                statement.setString(2, complaints.getDescription());

                try{
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                int autoIncrementedID = generatedKeys.getInt(1);
                                System.out.println("Auto-incremented ID: " + autoIncrementedID);
                                complaints.setComplaintID(autoIncrementedID);
                            } else {
                                System.out.println("Failed to retrieve auto-incremented ID.");
                            }
                        }
                        System.out.println("Complaint inserted successfully!");
                        return true;
                    }
                } catch(SQLException e){
                    System.out.println("Failed to insert Complaint.");
                    return false;
                }
            }
        } finally {
            dataBaseConnector.closeConnection();
        }
        return false;
    }

    public static Boolean InsertPayment(PaymentMethod payment) throws SQLException {
        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "INSERT INTO payment (customerid , cardnumber , expiration_date , cvv , type) VALUES ( ? , ? , ? , ? , ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, payment.getCustomerID());
                statement.setString(2, payment.getCardNumber());

                YearMonth expirationYearMonth = payment.getExpirationDate();
                LocalDate expirationLocalDate = expirationYearMonth.atDay(1); // Assuming day is 1 for the start of the month
                java.sql.Date expirationDate = java.sql.Date.valueOf(expirationLocalDate);
                statement.setDate(3, expirationDate);

                statement.setString(4, payment.getCvv());
                statement.setString(5, payment.getType());

                try{
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                int autoIncrementedID = generatedKeys.getInt(1);
                                System.out.println("Auto-incremented ID: " + autoIncrementedID);
                                payment.setCardID(autoIncrementedID);
                            } else {
                                System.out.println("Failed to retrieve auto-incremented ID.");
                            }
                        }
                        System.out.println("Payment inserted successfully!");
                        return true;
                    }
                } catch(SQLException e){
                    System.out.println("Failed to insert Payment.");
                    return false;
                }
            }
        } finally {
            dataBaseConnector.closeConnection();
        }
        return false;
    }

}
