import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class RetrieveFromDatabase {
    private static final DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();
    public static List<Trip> retrieveAvailableTrips() {
        List<Trip> trips = new ArrayList<>();

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
    public static List<Driver> retrieveTheDrivers() {
        List<Driver> driverList = new ArrayList<>();

        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "SELECT * FROM Drivers;";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet rS = statement.executeQuery()) {
                    while (rS.next()) {
                        Driver driver = new Driver(rS.getString("firstname"), rS.getString("lastname"),rS.getString("email"), rS.getString("driver_password") , rS.getString("phone"), rS.getInt("driverID"),rS.getInt("number_of_trips"), rS.getInt("rating"), rS.getBoolean("have_car") );
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
    public static List<Driver> retrieveTheDriversWithoutCars() {
        List<Driver> driverList = new ArrayList<>();

        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "SELECT * FROM Drivers where have_car = 0;";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet rS = statement.executeQuery()) {
                    while (rS.next()) {
                        Driver driver = new Driver(rS.getString("firstname"), rS.getString("lastname"),rS.getString("email"), rS.getString("driver_password") , rS.getString("phone"), rS.getInt("driverID"),rS.getInt("number_of_trips"), rS.getInt("rating"), rS.getBoolean("have_car") );
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
    public static List<Customer> retrieveTheCustomers() {
        List<Customer> customerList = new ArrayList<>();

        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "SELECT * FROM customers;";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet rS = statement.executeQuery()) {
                    while (rS.next()) {
                        Customer customer= new Customer(rS.getString("firstname"), rS.getString("lastname"),rS.getString("email"), rS.getString("UserPassword") , rS.getString("phone"), rS.getInt("customerID"),rS.getInt("Rating"), rS.getInt("balance") );
                        customerList.add(customer);
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } finally {
            dataBaseConnector.closeConnection();
        }

        return customerList;
    }
    public static List<Employee> retrieveTheEmployees() {
        List<Employee> employeeList = new ArrayList<>();

        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "SELECT * FROM EMPLOYEES;";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet rS = statement.executeQuery()) {
                    while (rS.next()) {
                        Employee employee= new Employee(rS.getString("firstname"), rS.getString("lastname"),rS.getString("email"), rS.getString("EmployeePassword") , rS.getString("phone"), rS.getInt("EmployeeID") );
                        employeeList.add(employee);
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } finally {
            dataBaseConnector.closeConnection();
        }

        return employeeList;
    }

}
