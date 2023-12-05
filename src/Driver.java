public class Driver {
    private  int driverID;
    private  int NumberOfTrips;
    private  int rating;
    private  String firstName;
    private  String lastName;
    private  String email;
    private  String Password;
    private  String phone;

    private  boolean haveCar;

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public int getNumberOfTrips() {
        return NumberOfTrips;
    }

    public void setNumberOfTrips(int numberOfTrips) {
        NumberOfTrips = numberOfTrips;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isHaveCar() {
        return haveCar;
    }

    public void setHaveCar(boolean haveCar) {
        this.haveCar = haveCar;
    }
}
