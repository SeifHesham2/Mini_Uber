import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static javax.management.remote.JMXConnectorFactory.connect;

public class Employee {
    private int employeeID;
    private  String firstName;
    private  String lastName;
    private  String email;
    private  String Password;
    private  String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public static void insertEmployee(Employee employee) throws SQLException {
        DataBaseConnector dataBaseConnector = new DataBaseConnector();
        Connection connection = dataBaseConnector.connectToDatabase();
        try {
            String sql = "INSERT INTO employees (firstname, lastname, email, phone , employeepassword) VALUES (?, ?, ?, ? , ?)";
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
