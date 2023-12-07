import java.sql.SQLException;

import static javax.management.remote.JMXConnectorFactory.connect;

public class Employee extends Person {

    public Car AddCar(int numberOfSeats, String plateNumber, String carType, String carColor, String carModel) throws SQLException {
        Car newCar = new Car(numberOfSeats, plateNumber, carType, carColor, carModel);
        InsertToDatabase.InsertCar(newCar);
        return newCar;
    }

    public void AssignCarToDriver(Driver driver, Car existingCar) throws SQLException {
        if (!driver.isHaveCar()) {
                driver.setHaveCar(true);
                UpdateDataBase.UpdateDriver(driver.isHaveCar(), driver.getId());
                existingCar.setDriverID(driver.getId());
                UpdateDataBase.UpdateCar(driver.getId(), existingCar.getCarID());
            }
           else {
            System.out.println("The driver already has a car");
        }
    }
}


