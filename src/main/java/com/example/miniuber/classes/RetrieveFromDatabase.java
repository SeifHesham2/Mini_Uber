package com.example.miniuber.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RetrieveFromDatabase {
    private static final DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();
    public static List<Trip> retrieveAvailableTrips() {
        List<Trip> trips = new ArrayList<>();

        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "SELECT * FROM trips WHERE driverID IS NULL and is_finished = 0;";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet rS = statement.executeQuery()) {
                    while (rS.next()) {
                        Trip trip = new Trip(rS.getInt("tripID"), rS.getString("pickup_point"),
                                rS.getString("destination"), rS.getTimestamp("trip_time").toLocalDateTime(),
                                rS.getDouble("trip_price"), rS.getString("payment_method"),
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


    public static Driver retrieveDriver(int driverID) {
        Driver driver = null;
        try (Connection connection = dataBaseConnector.connectToDatabase();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Drivers WHERE driverid = ?")) {
            statement.setInt(1, driverID);
            try (ResultSet rS = statement.executeQuery()) {
                if (rS.next()) {
                    driver = new Driver(
                            rS.getString("firstname"),
                            rS.getString("lastname"),
                            rS.getString("email"),
                            rS.getString("driver_password"),
                            rS.getString("phone"),
                            rS.getInt("driverID"),
                            rS.getInt("number_of_trips"),
                            rS.getBoolean("have_car"),
                            rS.getDouble("rating")
                    );
                } else {
                    System.out.println("The Driver ID is NotFound !!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return driver;
    }

    public static Car retrieveCar(int driverID) {
        Car car = null;
        try (Connection connection = dataBaseConnector.connectToDatabase();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Cars WHERE driverid = ?")) {
            statement.setInt(1, driverID);
            try (ResultSet rS = statement.executeQuery()) {
                if (rS.next()) {
                    car = new Car(
                            rS.getInt("carID"),
                            rS.getInt("number_of_seats"),
                            rS.getString("plate_number"),
                            rS.getString("car_type"),
                            rS.getString("car_color"),
                            rS.getString("car_model"),
                            rS.getInt("driverID")
                    );
                } else {
                    System.out.println("The Car ID is NotFound !!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return car;
    }

    public static List<Driver> retrieveTheDriversWithoutCars() {
        List<Driver> driverList = new ArrayList<>();

        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "SELECT * FROM Drivers where have_car = 0;";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet rS = statement.executeQuery()) {
                    while (rS.next()) {
                        Driver driver = new Driver(rS.getString("firstname"),
                                rS.getString("lastname"),
                                rS.getString("email"),
                                rS.getString("driver_password") ,
                                rS.getString("phone"),
                                rS.getInt("driverID"),
                                rS.getInt("number_of_trips"),
                                rS.getBoolean("have_car"),
                                rS.getDouble("rating"));
                        driverList.add(driver);
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } finally {
            dataBaseConnector.closeConnection();
        }

        return driverList;
    }

    public static List<Trip> retrieveDriverTripsHistory(int id) {
        List<Trip> trips = new ArrayList<>();

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
                                rS.getString("payment_method"), rS.getInt("customerID"));
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

        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "SELECT * FROM trips WHERE customerID = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet rS = statement.executeQuery()) {
                    while (rS.next()) {
                        Trip trip = new Trip(rS.getInt("tripID"), rS.getInt("driverID"), rS.getString("pickup_point"),
                                rS.getString("destination"), rS.getTimestamp("trip_time").toLocalDateTime(),
                                rS.getDouble("trip_price"),
                                rS.getString("payment_method"), rS.getBoolean("is_finished"));
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

    public static Complaints retrieveComplaint(int complaintID) {
        Complaints complaint = null;
        try (Connection connection = dataBaseConnector.connectToDatabase();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM complaints WHERE complaintID = ?")) {
            statement.setInt(1, complaintID);
            try (ResultSet rS = statement.executeQuery()) {
                if (rS.next()) {
                    complaint = new Complaints(
                          rS.getInt("ComplaintID"),
                            rS.getInt("tripID"),
                            rS.getString("description"),
                            rS.getBoolean("opened")
                    );
                } else {
                    System.out.println("The Complaint ID is NotFound !!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  complaint;
    }

    public static Customer retrieveCustomer(int customerID) {
        Customer customer = null;
        try (Connection connection = dataBaseConnector.connectToDatabase();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Customers WHERE customerID = ?")) {
            statement.setInt(1, customerID);
            try (ResultSet rS = statement.executeQuery()) {
                if (rS.next()) {
                    customer = new Customer(
                            rS.getString("firstname"),
                            rS.getString("lastname"),
                            rS.getString("email"),
                            rS.getString("UserPassword"),
                            rS.getString("phone"),
                            rS.getInt("CustomerID")
                    );
                } else {
                    System.out.println("The CustomerID is NotFound !!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dataBaseConnector.closeConnection();
        }
        return customer;
    }


    public static Employee retrieveEmployee(int employeeID) {
        Employee employee = null;
        try (Connection connection = dataBaseConnector.connectToDatabase();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE employeeID = ?")) {
            statement.setInt(1, employeeID);
            try (ResultSet rS = statement.executeQuery()) {
                if (rS.next()) {
                    employee = new Employee(
                            rS.getString("firstname"),
                            rS.getString("lastname"),
                            rS.getString("email"),
                            rS.getString("EmployeePassword"),
                            rS.getString("phone"),
                            rS.getInt("EmployeeID")
                    );
                } else {
                    System.out.println("The EmployeeID is NotFound !!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            dataBaseConnector.closeConnection();
        }
        return employee;
    }

    public static String retrieveForgotCustomer(String email, String phone) {
        Customer customer = null;
        try (Connection connection = dataBaseConnector.connectToDatabase();
                PreparedStatement statement = connection.prepareStatement("SELECT UserPassword FROM Customers WHERE email = ? AND phone = ?")) {
            statement.setString(1, email);
            statement.setString(2, phone);
            try (ResultSet rS = statement.executeQuery()) {
                if (rS.next()) {
                    return rS.getString("UserPassword");
                } else {
                    System.out.println("The CustomerID is NotFound !!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dataBaseConnector.closeConnection();
        }
        return null;
    }

    public static int retrieveTrip(int tripID) {
        Customer customer = null;
        try (Connection connection = dataBaseConnector.connectToDatabase();
             PreparedStatement statement = connection.prepareStatement("SELECT customerID FROM trips WHERE tripID = ?")) {
            statement.setInt(1, tripID);
            try (ResultSet rS = statement.executeQuery()) {
                if (rS.next()) {
                    return rS.getInt("customerID");
                } else {
                    System.out.println("The tripID is NotFound !!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dataBaseConnector.closeConnection();
        }
        return 0;
    }
}
