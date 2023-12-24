package com.example.miniuber.classes;

import javafx.scene.control.Label;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Employee extends Person {

    public Employee()
    {

    }


    public Employee(String firstName, String lastName, String email, String password, String phone, int id)
    {
        super(firstName, lastName, email, password, phone, id);
    }


    public static Boolean AddCar(Car newCar) throws SQLException
    {
        return InsertToDatabase.InsertCar(newCar);
    }

    public static int RegisterDriver(String firstName, String lastName, String email, String password, String phone) throws SQLException {
        Driver driver = new Driver(firstName,lastName,email,password,phone);
        int valid = InsertToDatabase.InsertDriver(driver);
        return valid;
    }

    public static Boolean AssignOrChangeCarToDriver(int driverID, int carID, boolean forChange, Label errorLabel4) throws SQLException
    {
        HashMap<Integer, Car> carHashMap = CarsList();
        Car specificCar = carHashMap.get(carID);

        if (specificCar == null)
        {
            System.out.println("Invalid CarID. Car not found.");
            errorLabel4.setLayoutX(280);
            errorLabel4.setText("Invalid CarID. Car not found.");
            return false;
        }

        Driver driver = RetrieveFromDatabase.retrieveDriver(driverID);
        Car driverCar = RetrieveFromDatabase.retrieveCar(driverID);
        if(driver == null)
        {
            errorLabel4.setLayoutX(280.0);
            errorLabel4.setText("Driver ID doesn't exist.");
            return false;
        }

        if(driverCar == null && forChange)
        {
            errorLabel4.setLayoutX(280.0);
            errorLabel4.setText("Driver doesn't have a car.");
            return false;
        }
        if(driverCar != null)
        {
            if(driverCar.getCarID() == carID)
            {
                errorLabel4.setLayoutX(210.0);
                errorLabel4.setText("You are trying to re-assign the same car to the same driver.");
                return false;
            }
        }

        if (forChange)
            driver.setHaveCar(false);

        if (!driver.isHaveCar())
        {
            if(forChange)
                UpdateDataBase.UpdateCarDriverID(driver.getId());

            Car car = carHashMap.get(carID);

            if (car != null)
            {
                driver.setHaveCar(true);
                UpdateDataBase.UpdateDriver(driver.isHaveCar(), driver.getId());
                car.setDriverID(driver.getId());
                UpdateDataBase.UpdateCar(driver.getId(), car.getCarID());
                return true;
            }
            else
            {
                System.out.println("Invalid CarID. Car not found.");
                errorLabel4.setText("Invalid CarID. Car not found.");
                return false;
            }
        }
        else
        {
            errorLabel4.setLayoutX(270.0);
            System.out.println("The driver already has a car");
            errorLabel4.setText("The driver already has a car.");
            return false;
        }
    }

    public static List<Complaints> viewComplaints()
    {
        List<Complaints> complaintsList = new ArrayList<>();
        complaintsList = RetrieveFromDatabase.retrieveComplaints();
        return complaintsList;
    }

    public static Boolean updateComplaint(int complaintID, Label errorLabel2) throws SQLException
    {
        Complaints complaint = RetrieveFromDatabase.retrieveComplaint(complaintID);
        if(complaint == null)
        {
            errorLabel2.setLayoutX(291.0);
            errorLabel2.setText("This complaint doesn't exist.");
            return false;
        }
        if (!complaint.isOpened())
        {
            UpdateDataBase.UpdateComplaintsStatus(complaint.getComplaintID());
            complaint.setOpened(true);
            return true;
        }
        else
        {
            errorLabel2.setLayoutX(255.0);
            errorLabel2.setText("This complaint has already been opened.");
            return false;
        }
    }

    public static HashMap<Integer,Car> CarsList()
    {
        List<Car> carList = new ArrayList<>();
        HashMap<Integer, Car> carHashMap = new HashMap<Integer, Car>();
        carList = RetrieveFromDatabase.retrieveAvailableCars();

        for (Car c : carList)
        {
            carHashMap.put(c.getCarID(), c);
            System.out.println("The Car ID is = " + c.getCarID());
            System.out.println("The Number OF seats = "+ c.getNumberOfSeats());
            System.out.println("Car plate Number = "+ c.getPlateNumber());
            System.out.println("Car Model is = " +c.getCarModel());
            System.out.println("The Car Color is =  " +c.getCarColor());
            System.out.println("________________________________________________________");
        }
        return carHashMap;
    }

    public static void AssignCarToAllDrivers(Label errorLabel5, Label successLabel5) throws SQLException
    {
        List<Driver> driverList = RetrieveFromDatabase.retrieveTheDriversWithoutCars();
        List<Car> carList = RetrieveFromDatabase.retrieveAvailableCars();
        int numberOfCars = carList.size();
        int numberOfDrivers = driverList.size();
        int counter = Math.min(numberOfCars, numberOfDrivers);

        for (int i = 0; i < counter; i++)
        {
            driverList.get(i).setHaveCar(true);
            UpdateDataBase.UpdateDriver(driverList.get(i).isHaveCar(), driverList.get(i).getId());

            carList.get(i).setDriverID(driverList.get(i).getId());
            UpdateDataBase.UpdateCar(driverList.get(i).getId(), carList.get(i).getCarID());
        }

        if(numberOfCars == 0)
        {
            errorLabel5.setLayoutX(260);
            errorLabel5.setText("There are no cars available in the system.");
            System.out.println("You need to Add cars To The system");
        }
        else if(numberOfCars < numberOfDrivers)
        {
            errorLabel5.setLayoutX(215);
            errorLabel5.setText("There are insufficient cars compared to number of drivers.");
            successLabel5.setLayoutY(425);
            successLabel5.setText("But all available cars assigned to drivers.");
            System.out.println("You need to Add cars To The system");
        }
        else
        {
            successLabel5.setLayoutX(270.0);
            successLabel5.setText("All drivers are now assigned to cars.");
        }
    }
    public static void AutomaticCarAssignment()
    {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() ->
        {
            try
            {
                List<Driver> driverList = RetrieveFromDatabase.retrieveTheDriversWithoutCars();
                List<Car> carList = RetrieveFromDatabase.retrieveAvailableCars();
                int numberOfCars = carList.size();
                int numberOfDrivers = driverList.size();
                int counter = Math.min(numberOfCars, numberOfDrivers);

                if(numberOfCars < numberOfDrivers)
                    System.out.println("You need to Add cars To The system");

                for (int i = 0; i < counter; i++)
                {
                    driverList.get(i).setHaveCar(true);
                    UpdateDataBase.UpdateDriver(driverList.get(i).isHaveCar(), driverList.get(i).getId());

                    carList.get(i).setDriverID(driverList.get(i).getId());
                    UpdateDataBase.UpdateCar(driverList.get(i).getId(), carList.get(i).getCarID());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }, 1, 24, TimeUnit.HOURS);
    }
}








