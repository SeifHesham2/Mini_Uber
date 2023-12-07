public class Car {
    private  int carID;
    private  int numberOfSeats;
    private  String plateNumber;
    private  String carType;
    private  String carColor;
    private  String carModel;

    private  int driverID;

    public Car() {

    }

    public Car(int carID ,int numberOfSeats, String plateNumber, String carType, String carColor, String carModel, int driverID) {
        this.numberOfSeats = numberOfSeats;
        this.plateNumber = plateNumber;
        this.carType = carType;
        this.carColor = carColor;
        this.carModel = carModel;
        this.driverID = driverID;
        this.carID=carID;
    }

    public Car(int numberOfSeats, String plateNumber, String carType, String carColor, String carModel) {
        this.numberOfSeats = numberOfSeats;
        this.plateNumber = plateNumber;
        this.carType = carType;
        this.carColor = carColor;
        this.carModel = carModel;
    }


    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }
}
