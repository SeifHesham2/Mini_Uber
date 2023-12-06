import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
public class InsertToDatabase {
    public static void insertEmployee(Employee employee) throws SQLException
    {
        DataBaseConnector dataBaseConnector = new DataBaseConnector();
        Connection connection = dataBaseConnector.connectToDatabase();
        try
        {
            String sql = "INSERT INTO employees (firstname, lastname, phone ) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql))
            {
                statement.setString(1, employee.getFirstName());
                statement.setString(2, employee.getLastName());
                statement.setString(3, employee.getPhone());

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0)
                {
                    System.out.println("Employee inserted successfully!");
                } else
                {
                    System.out.println("Failed to insert employee.");
                }
            }
        } finally
        {
            dataBaseConnector.closeConnection();
        }
    }
    public static void InsertDriver(Driver driver) throws  SQLException
    {
        DataBaseConnector dataBaseConnector= new DataBaseConnector();
        Connection connection = dataBaseConnector.connectToDatabase();
        try
        {
            String sql = "INSERT INTO drivers (firstname , lastname , email , phone , driver_password) VALUES ( ? , ? , ? , ? , ?)";
            try(PreparedStatement statement = connection.prepareStatement(sql))
            {
                statement.setString(1,driver.getFirstName());
                statement.setString(2, driver.getLastName());
                statement.setString(3, driver.getEmail());
                statement.setString(4,driver.getPhone());
                statement.setString(5,driver.getPassword());
                int rowsInserted= statement.executeUpdate();
                if (rowsInserted > 0)
                {
                    System.out.println("Driver inserted successfully!");
                } else
                {
                    System.out.println("Failed to insert Driver.");
                }
            }
        }
        finally {
            dataBaseConnector.closeConnection();
                 }

    }
    public static void InsertTrip(Trip trip) throws  SQLException
    {
        DataBaseConnector dataBaseConnector= new DataBaseConnector();
        Connection connection = dataBaseConnector.connectToDatabase();
        try
        {
            String sql = "INSERT INTO trips (pickup_point , destination , trip_time , trip_price ) VALUES ( ? , ? , ? , ?)";
            try(PreparedStatement statement = connection.prepareStatement(sql))
            {
                statement.setString(1,trip.getPickupPoint());
                statement.setString(2, trip.getDestination());
                Timestamp timestamp = Timestamp.valueOf(trip.getTripTime());
                statement.setTimestamp(3,timestamp);
                statement.setDouble(4, trip.getTripPrice());
                int rowsInserted= statement.executeUpdate();
                if (rowsInserted > 0)
                {
                    System.out.println("Trip inserted successfully!");
                } else
                {
                    System.out.println("Failed to insert Trip.");
                }
            }
        }
        finally {
            dataBaseConnector.closeConnection();
        }

    }
    public static void InsertCar(Car car) throws  SQLException
    {
        DataBaseConnector dataBaseConnector= new DataBaseConnector();
        Connection connection = dataBaseConnector.connectToDatabase();
        try
        {
            String sql = "INSERT INTO cars (plate_number , car_type , car_color , car_model , number_of_seats  ) VALUES ( ? , ? , ? , ? , ?)";
            try(PreparedStatement statement = connection.prepareStatement(sql))
            {
                statement.setString(1,car.getPlateNumber());
                statement.setString(2, car.getCarType());
                statement.setString(3,car.getCarColor());
                statement.setString(4,car.getCarModel());
                statement.setInt(5,car.getNumberOfSeats());
                int rowsInserted= statement.executeUpdate();
                if (rowsInserted > 0)
                {
                    System.out.println("car inserted successfully!");
                } else
                {
                    System.out.println("Failed to insert car.");
                }
            }
        }
        finally {
            dataBaseConnector.closeConnection();
        }

    }

}
