import javax.xml.crypto.Data;
import java.sql.*;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
  public static void main(String[] args) throws SQLException {
    Driver driver = new Driver();
    driver.setHaveCar(false);
    driver.setRating(4);
    driver.setEmail("seifhesham2030@gmail.com");
    driver.setFirstName("seif");
    driver.setNumberOfTrips(50);
    driver.setPhone("01099688147");
    driver.setLastName("hesham");
    driver.setPassword("123");
    InsertToDatabase.InsertDriver(driver);
    Employee employee = new Employee();
    System.out.println(driver.getId());
    Car newCar = employee.AddCar(4, "Kodkos1", "SUV", "RED", "2019");
    employee.AssignCarToDriver(driver,newCar);

    Trip tripp = new Trip();
    tripp.setDestination("Mama");
    tripp.setPickupPoint("Baba");
    tripp.setTripPrice(50);
    tripp.setTripTime("2023-12-06 22:00:00");
    InsertToDatabase.InsertTrip(tripp);

    driver.AcceptTrip();

    /*Employee emp = new Employee();
    emp.setEmail("xx@gmail.com");
    emp.setFirstName("Zahran");
    emp.setLastName("Seif");
    emp.setPassword("12345678");
    emp.setPhone("01554117204");
    InsertToDatabase.InsertEmployee(emp);*/
  }

}