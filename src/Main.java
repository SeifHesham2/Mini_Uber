import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
  public static void main(String[] args) throws SQLException {

  /*
  Employee.AutomaticCarAssignment();
  Very Important Function For the main
   */



   /*   Driver driver = new Driver();
      driver.setHaveCar(false); driver.setRating(4);
      driver.setEmail("seifhesham20d30@gmail.com");
      driver.setFirstName("seif");
      driver.setNumberOfTrips(50);
      driver.setPhone("01099688147");
      driver.setLastName("hesham");
      driver.setPassword("123");
      InsertToDatabase.InsertDriver(driver);*/

     /*Employee employee = new Employee();
     employee.AssignCarToAllDrivers();*/
      /*employee.AssignOrChangeCarToDriver(driver,false);
      employee.AssignOrChangeCarToDriver(driver,true);*/

     // Employee.AssignCarToAllDrivers();


      ////////////////// CUSTOMER TESTING//////////////
/*
    Customer customer = new Customer();
    customer.setFirstName("Ashraf");
    customer.setLastName("ABO ASHRAF");
    customer.setEmail("lolaa@gmail.com");
    customer.setPhone("01099688147");
    customer.setPassword("123");
    InsertToDatabase.InsertCustomer(customer);
    PaymentMethod paymentMethod = PaymentFactory.getPaymentMethod("Visa");
    customer.RequestTrip("East", "West", "2023-12-07 22:00:00", 60, paymentMethod, customer.getId());
    customer.RequestTrip("Baba", "Mama", "2023-12-07 21:00:00", 90, paymentMethod, customer.getId());

    List<Trip> tripsList = customer.PreviousTripsDetails(customer.getId());
    for (Trip trip : tripsList) {
      System.out.println(trip.getTripID());
      System.out.println(trip.getDestination());
      System.out.println(trip.getPickupPoint());
      System.out.println(trip.getTripPrice());
    }

    customer.SendComplaint("BAD DRIVER TALA3 ro5sa ezayy ?", 12);
*/

    ////////////////// CUSTOMER TESTING//////////////

    // Trip tripp = new Trip();
    // tripp.setDestination("Mama");
    // tripp.setPickupPoint("Baba");
    // tripp.setTripPrice(50);
    // tripp.setTripTime("2023-12-06 22:00:00");
    // PaymentMethod paymentMethod = new Visa();
    // tripp.setPaymentMethod(paymentMethod);
    // tripp.setCustomerID(1);
    // InsertToDatabase.InsertTrip(tripp);

    /* driver.AcceptTrip(); */

    /*
     * Employee emp = new Employee();
     * emp.setEmail("xx@gmail.com");
     * emp.setFirstName("Zahran");
     * emp.setLastName("Seif");
     * emp.setPassword("12345678");
     * emp.setPhone("01554117204");
     * InsertToDatabase.InsertEmployee(emp);
     */

    /*
     * Employee seif = new Employee();
     * List<Complaints> complaintsList = seif.viewComplaints();
     * for(Complaints complaint : complaintsList){
     * System.out.println("Our Complaint ID --> "+ complaint.getComplaintID());
     * System.out.println("Our Complaint Description --> "+complaint.getDescription(
     * ));
     * System.out.println("Our Complaint TripID --> "+complaint.getTripID());
     * System.out.println("IS Complaint Opened ? --> "+complaint.isOpened());
     * System.out.println("----------------------------------------------");
     * seif.updateComplaint(complaint);
     * System.out.println(" 2 Our Complaint ID --> "+ complaint.getComplaintID());
     * System.out.println(" 2 Our Complaint Description --> "+complaint.
     * getDescription());
     * System.out.println(" 2 Our Complaint TripID --> "+complaint.getTripID());
     * System.out.println( "2 IS Complaint Opened ? --> "+complaint.isOpened());
     * System.out.println("----------------------------------------------");
     */

    /* } */
    /*
     * Employee seif = new Employee();
     * seif.changeDriverCar(driver);
     */

  }

}