import java.sql.SQLException;

import static javax.management.remote.JMXConnectorFactory.connect;

public class Employee extends Person {

    public Car AddCar(int numberOfSeats, String plateNumber, String carType, String carColor, String carModel) throws SQLException {
        Car newCar = new Car(numberOfSeats, plateNumber, carType, carColor, carModel);
        InsertToDatabase.InsertCar(newCar);
        newCar.setCarID(ID.retrieveCarIdFromDatabase(newCar));
        return newCar;
    }

    public void AssignCarToDriver(Driver driver, Car existingCar) throws SQLException {
        if (!driver.isHaveCar()) {
                driver.setHaveCar(true);
                driver.setId(ID.retrieveDriverIdFromDatabase(driver));
                UpdateDataBase.UpdateDriver(driver);
                existingCar.setDriverID(driver.getId());
                UpdateDataBase.UpdateCar(existingCar, driver);
            }
           else {
            System.out.println("The driver already has a car");
        }
    }
}


