import java.sql.*;

public class InsertToDatabase {
    public static void InsertEmployee(Employee employee) throws SQLException {
        DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();
        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "INSERT INTO employees (firstname, lastname, phone ) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, employee.getFirstName());
                statement.setString(2, employee.getLastName());
                statement.setString(3, employee.getPhone());

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int autoIncrementedID = generatedKeys.getInt(1);
                            System.out.println("Auto-incremented ID: " + autoIncrementedID);
                            employee.setId(autoIncrementedID);
                        } else {
                            System.out.println("Failed to retrieve auto-incremented ID.");
                        }
                    }
                    System.out.println("Employee inserted successfully!");
                } else {
                    System.out.println("Failed to insert employee.");
                }
            }
        } finally {
            dataBaseConnector.closeConnection();
        }
    }

    public static void InsertDriver(Driver driver) throws SQLException {
        DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();
        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "INSERT INTO drivers (firstname , lastname , email , phone , driver_password) VALUES ( ? , ? , ? , ? , ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, driver.getFirstName());
                statement.setString(2, driver.getLastName());
                statement.setString(3, driver.getEmail());
                statement.setString(4, driver.getPhone());
                statement.setString(5, driver.getPassword());
                // statement.setInt(6,driver.getRating());
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
                } else {
                    System.out.println("Failed to insert Driver.");
                }
            }
        } finally {
            dataBaseConnector.closeConnection();
        }

    }

    public static void InsertCustomer(Customer customer) throws SQLException {
        DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();
        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "INSERT INTO customers (firstname , lastname , email , phone , UserPassword) VALUES ( ? , ? , ? , ? , ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, customer.getFirstName());
                statement.setString(2, customer.getLastName());
                statement.setString(3, customer.getEmail());
                statement.setString(4, customer.getPhone());
                statement.setString(5, customer.getPassword());
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
                } else {
                    System.out.println("Failed to insert Customer.");
                }
            }
        } finally {
            dataBaseConnector.closeConnection();
        }

    }

    public static void InsertTrip(Trip trip) throws SQLException {
        DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();
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
                } else {
                    System.out.println("Failed to insert Trip.");
                }
            }
        } finally {
            dataBaseConnector.closeConnection();
        }

    }

    public static void InsertCar(Car car) throws SQLException {
        DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();
        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "INSERT INTO cars (plate_number , car_type , car_color , car_model , number_of_seats  ) VALUES ( ? , ? , ? , ? , ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, car.getPlateNumber());
                statement.setString(2, car.getCarType());
                statement.setString(3, car.getCarColor());
                statement.setString(4, car.getCarModel());
                statement.setInt(5, car.getNumberOfSeats());
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
                    System.out.println("car inserted successfully!");
                } else {
                    System.out.println("Failed to insert car.");
                }
            }
        } finally {
            dataBaseConnector.closeConnection();
        }

    }

    public static void InsertComplaint(Complaints complaints) throws SQLException {
        DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();
        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "INSERT INTO complaints (tripID, description) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, Integer.toString(complaints.getTripID()));
                statement.setString(2, complaints.getDescription());

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
                } else {
                    System.out.println("Failed to insert complaint.");
                }
            }
        } finally {
            dataBaseConnector.closeConnection();
        }
    }

}
