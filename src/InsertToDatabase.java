import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertToDatabase {
    public static void insertEmployee(Employee employee) throws SQLException {
        DataBaseConnector dataBaseConnector = new DataBaseConnector();
        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "INSERT INTO employees (firstname, lastname, email, phone , employeePassword) VALUES (?, ?, ?, ? , ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, employee.getFirstName());
                statement.setString(2, employee.getLastName());
                statement.setString(3, employee.getEmail());
                statement.setString(4, employee.getPhone());
                statement.setString(5, employee.getPassword());
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Employee inserted successfully!");
                } else {
                    System.out.println("Failed to insert employee.");
                }
            }
        } finally {
            dataBaseConnector.closeConnection();
        }
    }
}
