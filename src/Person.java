public abstract  class Person {
    private  String firstName;
    private  String lastName;
    private  String email ;
    private  String Password;
    private  String phone;

    public Person() {
    }

    public Person(String firstName, String lastName, String email, String password, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        Password = password;
        this.phone = phone;
    }

    public Person(String firstName, String lastName, String email, String password, String phone, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        Password = password;
        this.phone = phone;
        this.id = id;
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
