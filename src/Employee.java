import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static javax.management.remote.JMXConnectorFactory.connect;

public class Employee extends Person {

    public Car AddCar(int numberOfSeats, String plateNumber, String carType, String carColor, String carModel) throws SQLException
    {
        Car newCar = new Car(numberOfSeats, plateNumber, carType, carColor, carModel);
        InsertToDatabase.InsertCar(newCar);
        return newCar;
    }

    public void AssignCarToDriver(Driver driver, Car existingCar) throws SQLException
    {
        if (!driver.isHaveCar()) {
            driver.setHaveCar(true);
            UpdateDataBase.UpdateDriver(driver.isHaveCar(), driver.getId());
            existingCar.setDriverID(driver.getId());
            UpdateDataBase.UpdateCar(driver.getId(), existingCar.getCarID());
        } else {
            System.out.println("The driver already has a car");
        }
    }

    public List<Complaints> viewComplaints()
    {
     List<Complaints> complaintsList = new ArrayList<>();
      complaintsList = RetrieveFromDatabase.retrieveComplaints();
      return  complaintsList;
    }

    public  void updateComplaint(Complaints complaint) throws SQLException
    {
        if(!complaint.isOpened())
        {
            UpdateDataBase.UpdateComplaintsStatus(complaint.getComplaintID());
            complaint.setOpened(true);
        }
    }

    public void changeDriverCar(Driver driver) throws SQLException
    {
       List<Car> carList = new ArrayList<>();
       HashMap<Integer,Car> carHashMap = new HashMap<Integer, Car>();
       carList=RetrieveFromDatabase.retrieveAvailableCars();
       for(Car c : carList)
       {
           carHashMap.put(c.getCarID(),c);
           System.out.println(c.getNumberOfSeats());
           System.out.println(c.getCarID());
           System.out.println(c.getPlateNumber());
           System.out.println(c.getCarModel());
           System.out.println(c.getCarColor());
       }
        driver.setHaveCar(false);
        UpdateDataBase.UpdateCarDriverID(driver.getId());
        System.out.println("choose your id ");
        Scanner sc = new Scanner(System.in);
        int carID = sc.nextInt();
        Car car = carHashMap.get(carID);
        AssignCarToDriver(driver,car);
    }



}

