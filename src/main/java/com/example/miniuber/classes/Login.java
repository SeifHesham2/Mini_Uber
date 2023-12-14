package com.example.miniuber.classes;

public class Login {
    public static Driver DriverLogin(String Email , String Password){
        int driverID= DataBaseAuthentication.DriverAuthentication(Email,Password);
        return RetrieveFromDatabase.retrieveDriver(driverID);
    }
    public static Customer CustomerLogin(String Email , String Password) {
        int customerID=DataBaseAuthentication.CustomerAuthentication(Email,Password);
        return RetrieveFromDatabase.retrieveCustomer(customerID);

    }
    public static Employee EmployeeLogin(String Email , String Password) {
        int employeeID = DataBaseAuthentication.EmployeeAuthentication(Email,Password);
        return  RetrieveFromDatabase.retrieveEmployee(employeeID);
    }
}