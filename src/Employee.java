import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static javax.management.remote.JMXConnectorFactory.connect;

public class Employee extends Person {

    public Employee()
    {

    }


    public Employee(String firstName, String lastName, String email, String password, String phone, int id)
    {
        super(firstName, lastName, email, password, phone, id);
    }


    public Car AddCar(int numberOfSeats, String plateNumber, String carType, String carColor, String carModel) throws SQLException
    {
        Car newCar = new Car(numberOfSeats, plateNumber, carType, carColor, carModel);
        InsertToDatabase.InsertCar(newCar);
        return newCar;
    }



    public void AssignOrChangeCarToDriver(int DriverID, boolean forChange) throws SQLException
    {
        Driver driver = RetrieveFromDatabase.retrieveDriver(DriverID);
        if (forChange)
            driver.setHaveCar(false);

        if (!driver.isHaveCar())
        {
            HashMap<Integer, Car> carHashMap = Carslist();

            if(forChange)
                UpdateDataBase.UpdateCarDriverID(driver.getId());

            System.out.println("Choose CarID for the car you want For The Driver Name " + driver.getFirstName() + " " + driver .getLastName() + " " + " Who has id = "+ driver.getId());
            Scanner sc = new Scanner(System.in);
            int carID = sc.nextInt();
            Car car = carHashMap.get(carID);

            if (car != null)
            {
                driver.setHaveCar(true);
                UpdateDataBase.UpdateDriver(driver.isHaveCar(), driver.getId());
                car.setDriverID(driver.getId());
                UpdateDataBase.UpdateCar(driver.getId(), car.getCarID());
            } else
            {
                System.out.println("Invalid CarID. Car not found.");
            }
        }

        else
        {
            System.out.println("The driver already has a car");
        }
    }



    public List<Complaints> viewComplaints()
    {
        List<Complaints> complaintsList = new ArrayList<>();
        complaintsList = RetrieveFromDatabase.retrieveComplaints();
        return complaintsList;
    }

    public void updateComplaint(int complaintID) throws SQLException
    {
        Complaints complaint = RetrieveFromDatabase.retrieveComplaint(complaintID);
        if (!complaint.isOpened())
        {
            UpdateDataBase.UpdateComplaintsStatus(complaint.getComplaintID());
            complaint.setOpened(true);
        }
    }
    public HashMap<Integer,Car> Carslist()
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

    public  void AssignCarToAllDrivers() throws SQLException
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
        }, 0, 24, TimeUnit.HOURS);
    }

    public void RegisterDriver(String firstName, String lastName, String email, String password, String phone) throws SQLException {
        Driver driver = new Driver(firstName,lastName,email,password,phone);
        InsertToDatabase.InsertDriver(driver);
    }
}








